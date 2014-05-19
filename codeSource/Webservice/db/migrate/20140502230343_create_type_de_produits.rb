class CreateTypeDeProduits < ActiveRecord::Migration
  def change
    create_table :type_de_produits do |t|
    	t.string :type_produit,     null: false

      t.timestamps
    end
  end
end
