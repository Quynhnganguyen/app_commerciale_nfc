class Produit < ActiveRecord::Base
	belongs_to :magasin
	has_one :nfc

	belongs_to :source
	belongs_to :type_de_produit
end
