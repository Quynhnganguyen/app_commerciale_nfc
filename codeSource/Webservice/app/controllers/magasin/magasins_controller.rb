class Magasin::MagasinsController < ApplicationController
  
  def index
  		@vendeur = vendeur
      @magasin = Magasin.find(@vendeur.magasin_id)
  end

private
  def vendeur
  	if current_vendeur.id
      id = current_vendeur.id
      Vendeur.find(current_vendeur.id)
    end
  end

end
