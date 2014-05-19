class CreateMagasinClients < ActiveRecord::Migration
  def change
    create_table :magasin_clients do |t|

      t.timestamps
    end
  end
end
