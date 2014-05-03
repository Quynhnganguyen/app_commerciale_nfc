class CreateListeNoires < ActiveRecord::Migration
  def change
    create_table :liste_noires do |t|

      t.timestamps
    end
  end
end
