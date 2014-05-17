class Client::ProduitsController < ApplicationController
	def index
    	@produits = Produit.where(magasin_id: params[:magasin_id])
  	end


end
