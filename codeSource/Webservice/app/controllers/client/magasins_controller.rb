class Client::MagasinsController < ApplicationController
	def index
      @magasins = Magasin.all
  	end

end
