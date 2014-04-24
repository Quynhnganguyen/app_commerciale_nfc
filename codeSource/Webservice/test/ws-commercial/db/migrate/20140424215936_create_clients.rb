class CreateClients < ActiveRecord::Migration
  def change
    create_table :clients do |t|
    	t.string :nom_client,     		null: false
    	t.string :prenom_client,  		null: false
    	t.string :adresse_mail_client, 	null: false
      t.timestamps
    end
  end
end
