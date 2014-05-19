class CreateListeAcheters < ActiveRecord::Migration
  def change
    create_table :liste_acheters do |t|

      t.timestamps
    end
  end
end
