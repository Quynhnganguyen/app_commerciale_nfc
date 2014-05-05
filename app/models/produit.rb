class Produit < ActiveRecord::Base
	belongs_to :magasin

	belongs_to :source
	belongs_to :type_de_produit
end
