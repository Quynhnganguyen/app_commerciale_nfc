class Client::ClientsController < ApplicationController
  def index
  	@magasins = Magasin.all 
  end

  def liste_sources
    @sources = Source.where(magasin_id: params[:magasin_id])
    results = @sourses.map {|p| {
                  :source_id => p.id,
                  :source => p.pays, 
                  :magasin => p.magasin.nom_magasin}}
    render json: results
  end

  def liste_types
    @types = TypeDeProduit.where(magasin_id: params[:magasin_id])
    results = @types.map {|p| {
                  :type_id => p.id,
                  :type => p.type_produit, 
                  :magasin => p.magasin.nom_magasin}}
    render json: results
  end
  def liste_magasins
    @magasins = Magasin.all
    results = @magasins.map {|m| {
             
                  :magasin_id => m.id,
                  :magasin_nom => m.nom_magasin, 
                  :magasin_addresse => m.adresse_magasin}}
    render json: results
  end

  def liste_produits
    @produits = Produit.where(magasin_id: params[:magasin_id])
    results = @produits.map {|p| {
             
                  :produit_id => p.id,
                  :produit_nom => p.nom_produit, 
                  :nombre => p.nombre,
                  :source => p.source.pays,
                  :type => p.type_de_produit.type_produit,
                  :nfc => p.nfc_id,
                  :magasin => p.magasin.nom_magasin}}
    render json: results
  end

end