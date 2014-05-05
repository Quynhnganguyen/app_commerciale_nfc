class Admin::FranchisesController < ApplicationController

  def index
  	@franchises = Franchise.all
  end
  
  def new
    @franchise = Franchise.new
  end
 
  def create
    @franchise = Franchise.new(franchise_params)
 
    if @franchise.save
      redirect_to :action => 'index', notice: "The franchise has been successfully created."
  	else
  	 redirect_to :action => 'index'
    end
  end

  def edit
    @franchise = Franchise.find(params[:id])
  end
 
  def update
    @franchise = Franchise.find(params[:id])
    if @franchise.update_attributes(franchise_params)
      redirect_to :action => 'index', notice: "The franchise has been updated"
    end
  end
 
private

  def franchise_params
    params.require(:franchise).permit!
  end
end
