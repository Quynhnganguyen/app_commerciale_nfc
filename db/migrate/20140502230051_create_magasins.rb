class CreateMagasins < ActiveRecord::Migration
  def change
    create_table :magasins do |t|
    	t.string :nom_magasin,     null: false
    	t.string :adresse_magasin, null: false
      t.timestamps
    end
  end
end
