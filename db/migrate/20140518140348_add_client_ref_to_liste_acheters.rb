class AddClientRefToListeAcheters < ActiveRecord::Migration
  def change
    add_reference :liste_acheters, :client, index: true
  end
end
