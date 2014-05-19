class AddProduitRefToListeNoires < ActiveRecord::Migration
  def change
    add_reference :liste_noires, :produit, index: true
  end
end
