class Source < ActiveRecord::Base
	belongs_to :magasin
	has_many :produit
end

