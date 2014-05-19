class ProduitsController < ApplicationController
  def index
    	@produits = Produit.where(magasin_id: params[:magasin_id])
    	@magasin = Magasin.find(params[:magasin_id])
  	end
  	
end
