class AddClientRefToListeFavoris < ActiveRecord::Migration
  def change
    add_reference :liste_favoris, :client, index: true
  end
end
