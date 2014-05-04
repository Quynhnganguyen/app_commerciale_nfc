class Magasin < ActiveRecord::Base
	has_many :vendeur

	has_many :produit
	has_many :type_deproduit
	has_many :source

	belongs_to :franchise
end
