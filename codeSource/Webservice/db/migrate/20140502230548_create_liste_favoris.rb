class CreateListeFavoris < ActiveRecord::Migration
  def change
    create_table :liste_favoris do |t|

      t.timestamps
    end
  end
end
