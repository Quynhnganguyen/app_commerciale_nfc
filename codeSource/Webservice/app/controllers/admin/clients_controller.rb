class Admin::ClientsController < ApplicationController

  def index
  	@clients = Client.all
  end

  def show
    @clients = Client.all
  end
 
private

  def client_params
    params.require(:client).permit!
  end
end
