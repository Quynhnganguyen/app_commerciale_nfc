class ListeNoire < ActiveRecord::Base
	belongs_to :produit
	belongs_to :client
end
