class AddMagasinRefToProduits < ActiveRecord::Migration
  def change
    add_reference :produits, :magasin, index: true
  end
end
