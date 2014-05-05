class Admin::FranchisesController < ApplicationController

  def index
  	@franchises = Franchise.all
  end
  
  def new
    @context = context
    @franchise = @context.franchise.new
  end
 
  def create
    @context = context
    @franchise = @context.franchise.new(franchise_params)
 
    if @franchise.save
      redirect_to context_url(context), notice: "The franchise has been successfully created."
    end
  end

  def edit
    @context = context
    @franchise = @context.franchise.find(params[:id])
  end
 
  def update
    @context = context
    @franchise = @context.franchise.find(params[:id])
    if @franchise.update_attributes(franchise_params)
      redirect_to context_url(context), notice: "The franchise has been updated"
    end
  end
 
private

  def franchise_params
    params.require(:franchise).permit!
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
