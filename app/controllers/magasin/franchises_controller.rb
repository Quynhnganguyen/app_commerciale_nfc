class Magasin::FranchisesController < ApplicationController
  def index
  		@franchises = Franchises.all
  end

  private
  def vendeur
  	if current_vendeur.id
      id = current_vendeur.id
      Vendeur.find(current_vendeur.id)
    end
  end
end
