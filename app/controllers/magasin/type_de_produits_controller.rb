class Magasin::TypeDeProduitsController < ApplicationController
  def index
  	@vendeur = vendeur
  	@types = TypeDeProduit.where(magasin_id: @vendeur.magasin_id)
  end

  def new
    @context = context
    @type = @context.type_de_produit.new
  end
 
  def create
    @context = context
    @type = @context.type_de_produit.new(type_de_produit_params)
 
    if @type.save
      redirect_to context_url(context), notice: "The type of produit has been successfully created."
    end
  end

  def edit
    @context = context
    @type = @context.type_de_produit.find(params[:id])
  end
 
  def update
    @context = context
    @type = @context.type_de_produit.find(params[:id])
    if @type.update_attributes(type_de_produit_params)
      redirect_to context_url(context), notice: "The type of produit has been updated"
    end
  end

  def destroy
    @context = context
    @type = @context.type_de_produit.find(params[:id])
    @type.destroy

    respond_to do |format|
      format.html { redirect_to context_url(context) }
      format.json { head :no_content }
    end
  end

  private

  def type_de_produit_params
    params.require(:type_de_produit).permit!
  end
 
  def context
    if vendeur.magasin_id
      id = vendeur.magasin_id
      Magasin.find(vendeur.magasin_id)
    end
  end 

  def context_url(context)
    if Magasin === context
      magasin_type_de_produits_index_path(context)
    end
  end

  def vendeur
  	if current_vendeur.id
      id = current_vendeur.id
      Vendeur.find(current_vendeur.id)
    end
  end
end
