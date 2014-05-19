class ChangeTableName < ActiveRecord::Migration
  def change
  	rename_table :liste_favoris, :liste_favorises
  end
end
