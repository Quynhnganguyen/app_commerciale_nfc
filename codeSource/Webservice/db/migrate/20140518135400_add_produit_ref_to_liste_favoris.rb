class AddProduitRefToListeFavoris < ActiveRecord::Migration
  def change
    add_reference :liste_favoris, :produit, index: true
  end
end
