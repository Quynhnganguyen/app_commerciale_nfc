class Produit < ActiveRecord::Base
	belongs_to :magasin

	belongs_to :source
	belongs_to :type_de_produit

	has_many :liste_favorise
	has_many :liste_noire
	has_many :liste_acheter
end
