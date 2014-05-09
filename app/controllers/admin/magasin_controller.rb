class Admin::MagasinController < ApplicationController

  def index
    @context = context
    @magasins = Magasin.all
  end
  
  def new
    @context = context
    @magasin = @context.magasin.new
  end
 
  def create
    @context = context
    @magasin = @context.magasin.new(magasin_params)
 
    if @magasin.save
      redirect_to context_url(context), notice: "The magasin has been successfully created."
    end
  end

  def edit
    @context = context
    @magasin = @context.magasin.find(params[:id])
  end
 
  def update
    @context = context
    @magasin = @context.magasin.find(params[:id])
    if @magasin.update_attributes(magasin_params)
      redirect_to context_url(context), notice: "The magasin has been updated"
    end
  end
 
private

  def magasin_params
    params.require(:magasin).permit!
  end
 
  def context
    if params[:franchise_id]
      id = params[:franchise_id]
      Franchise.find(params[:franchise_id])
    end
  end 

  def context_url(context)
    if Franchise === context
      admin_magasin_index_path(context)
    end
  end
end
