class Admin::ClientController < ApplicationController
	 before_filter :authenticate_user!

  def index
    @users = Client.all
  end

  def show
    @user = Client.find(params[:id])
  end
  
end
