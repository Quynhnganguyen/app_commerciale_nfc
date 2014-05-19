class Magasin::SourcesController < ApplicationController
  def index
    @vendeur = vendeur
    @sources = Source.where(magasin_id: @vendeur.magasin_id)
  end

  def new
    @context = context
    @source = @context.source.new
  end
 
  def create
    @context = context
    @source = @context.source.new(source_params)
 
    if @source.save
      redirect_to context_url(context), notice: "The source of produit has been successfully created."
    end
  end

  def edit
    @context = context
    @source = @context.source.find(params[:id])
  end
 
  def update
    @context = context
    @source = @context.source.find(params[:id])
    if @source.update_attributes(source_params)
      redirect_to context_url(context), notice: "The source of produit has been updated"
    end
  end

  def destroy
    @context = context
    @source = @context.source.find(params[:id])
    @source.destroy

    respond_to do |format|
      format.html { redirect_to context_url(context) }
      format.json { head :no_content }
    end
  end

  private

  def source_params
    params.require(:source).permit!
  end
 
  def context
    if vendeur.magasin_id
      id = vendeur.magasin_id
      Magasin.find(vendeur.magasin_id)
    end
  end 

  def context_url(context)
    if Magasin === context
      magasin_sources_index_path(context)
    end
  end

  def vendeur
    if current_vendeur.id
      id = current_vendeur.id
      Vendeur.find(current_vendeur.id)
    end
  end
end
