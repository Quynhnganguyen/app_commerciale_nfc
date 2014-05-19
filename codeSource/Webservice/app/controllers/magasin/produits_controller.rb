class Magasin::ProduitsController < ApplicationController

	def index
    @vendeur = vendeur
    @produits = Produit.where(magasin_id: @vendeur.magasin_id)
  end

  def show
    @context = context
    @produit = @context.produit.find(params[:id])
  end

  def new
    @context = context
    @produit = @context.produit.new
  end
 
  def create
    @context = context
    @produit = @context.produit.new(produit_params)
 
    if @produit.save
      redirect_to context_url(context), notice: "The produit of produit has been successfully created."
    end

  end

  def edit
    @context = context
    @produit = @context.produit.find(params[:id])
  end
 
  def update
    @context = context
    @produit = @context.produit.find(params[:id])
    if @produit.update_attributes(produit_params)
      redirect_to context_url(context), notice: "The produit of produit has been updated"
    end
  end

  def destroy
    @context = context
    @produit = @context.produit.find(params[:id])
    @produit.destroy

    respond_to do |format|
      format.html { redirect_to context_url(context) }
      format.json { head :no_content }
    end
  end

  private

  def produit_params
    params.require(:produit).permit!
  end
 
  def context
    if vendeur.magasin_id
      id = vendeur.magasin_id
      Magasin.find(vendeur.magasin_id)
    end
  end 

  def context_url(context)
    if Magasin === context
      magasin_produits_index_path(context)
    end
  end

  def vendeur
    if current_vendeur.id
      id = current_vendeur.id
      Vendeur.find(current_vendeur.id)
    end
  end
end
