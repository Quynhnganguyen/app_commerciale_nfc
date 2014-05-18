class Client::ListeAchetersController < ApplicationController
	def index
		@client = client
		@produits= ListeAcheter.where(client_id: @client.id)
	end

  def new
    @client = client
    @achat = @client.liste_acheter.new
    @achat.save
  end
 
  def create
    @client = client
    @achat = @client.liste_acheter.new(liste_acheter_params)
 
    if @achat.save
       flash[:notice] =  "produit has been successfully added in liste à acheter."
    end
  end



private
  def liste_acheter_params
    params.require(:liste_acheter).permit(:client_id, :produit_id)
  end

  def client
  	if current_client.id
      id = current_client.id
      Client.find(current_client.id)
    end
  end

end
