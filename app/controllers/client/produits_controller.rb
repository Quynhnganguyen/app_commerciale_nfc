class Client::ProduitsController < ApplicationController
	def index
		@client = client
    	@produits = Produit.where(magasin_id: params[:magasin_id])
    	@magasin = Magasin.find(params[:magasin_id])
  	end
  	private
  def client
  	if current_client.id
      id = current_client.id
      Client.find(current_client.id)
    end
  end
end
