class CreateProduits < ActiveRecord::Migration
  def change
    create_table :produits do |t|

      t.timestamps
    end
  end
end
