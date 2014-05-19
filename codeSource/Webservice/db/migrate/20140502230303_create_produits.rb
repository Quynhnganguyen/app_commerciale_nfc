class CreateProduits < ActiveRecord::Migration
  def change
    create_table :produits do |t|
    	t.string :nom_produit,     null: false
    	t.float :prix,  null: false
    	t.integer :nombre, null: false
      t.timestamps
    end
  end
end
