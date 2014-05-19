class Admin::VendeursController < ApplicationController

  def index
  end
  
  def new
    @context = context
    @vendeur = @context.vendeur.new
  end
 
  def create
    @context = context
    @vendeur = @context.vendeur.new(vendeur_params)
 
    if @vendeur.save
      redirect_to context_url(context), notice: "The vendeur has been successfully created."
    end
  end

  def edit
    @context = context
    @vendeur = @context.vendeur.find(params[:id])
  end
 
  def update
    @context = context
    @vendeur = @context.vendeur.find(params[:id])
    if @vendeur.update_attributes(vendeur_params)
      redirect_to context_url(context), notice: "The vendeur has been updated"
    end
  end
 
private

  def vendeur_params
    params.require(:vendeur).permit!
  end
 
  def context
    if params[:magasin_id]
      id = params[:magasin_id]
      Magasin.find(params[:magasin_id])
    end
  end 

  def context_url(context)
    if Magasin === context
      admin_magasin_index_path(context)
    end
  end

end
