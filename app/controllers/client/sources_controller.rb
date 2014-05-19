class Client::SourcesController < ApplicationController
	def index
    	@sources = Source.where(magasin_id: params[:magasin_id])
  	end
end
