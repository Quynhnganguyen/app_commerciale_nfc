class AddMagasinRefToTypeDeProduits < ActiveRecord::Migration
  def change
    add_reference :type_de_produits, :magasin, index: true
  end
end
