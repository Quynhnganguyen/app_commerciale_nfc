class Api::ClientsController < ApplicationController
	def sign_in
    @client = Client.where(email: params[:email]).first
    if @client.blank?
      render json: { status: 'Invalid client', message: "Invalid client"}
    elsif @client.valid_password?(params[:password])
      render json: { status: "success", message: "Login success", clientname: @client.name, 
                     client_id: @client.id, password_token: @client.authentication_token}
    else
      @client = nil
      render json: { status: 'Invalid password', message: "Invalid password"}
    end
  end

  def sign_out
    current_client.reset_password_token!
    render text: 'Logout success'
  end

  def sign_up

  end

  def liste_magasins
    @magasins = Magasin.all
    results = @magasins.map {|m| {
             
                  :magasin_id => m.id,
                  :magasin_nom => m.nom_magasin, 
                  :magasin_addresse => m.adresse_magasin}}
    render json: results
  end

  def liste_produits #consulte les produit par magasin
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

  def getProduits
    # on peut consulter les produit par source/type/prix/
  end
end
