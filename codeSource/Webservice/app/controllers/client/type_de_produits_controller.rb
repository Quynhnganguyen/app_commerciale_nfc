class Client::TypeDeProduitsController < ApplicationController
	def index
    	@types = TypeDeProduit.where(magasin_id: params[:magasin_id])
  	end
end
