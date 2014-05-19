class CreateListeCoureDejaFaites < ActiveRecord::Migration
  def change
    create_table :liste_coure_deja_faites do |t|

      t.timestamps
    end
  end
end
