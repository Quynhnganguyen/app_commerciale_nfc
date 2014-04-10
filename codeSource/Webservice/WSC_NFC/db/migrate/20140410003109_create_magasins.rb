class CreateMagasins < ActiveRecord::Migration
  def change
    create_table :magasins do |t|
      t.text :nomMagasin
      t.text :adresse
      t.integer :id_franchise

      t.timestamps
    end
  end
end
