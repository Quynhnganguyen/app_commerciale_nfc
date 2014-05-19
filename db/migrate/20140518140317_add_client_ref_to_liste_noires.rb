class AddClientRefToListeNoires < ActiveRecord::Migration
  def change
    add_reference :liste_noires, :client, index: true
  end
end
