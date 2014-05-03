class CreateTypeDeProduits < ActiveRecord::Migration
  def change
    create_table :type_de_produits do |t|

      t.timestamps
    end
  end
end
