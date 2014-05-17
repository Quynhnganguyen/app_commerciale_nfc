class MagasinsController < ApplicationController
  def index
  	@magasins = Magasin.all 
  	results = @magasins.map {|m| {
             
                  :magasin_id => m.id,
                  :magasin_nom => m.nom_magasin, 
                  :magasin_addresse => m.adresse_magasin}}
    render json: results
  end


end
