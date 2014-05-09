class AddAuthenTokenToDevise < ActiveRecord::Migration
  def change
  	remove_column :clients, :token_authenticatable
  	remove_column :admins, :token_authenticatable
  	remove_column :vendeurs, :token_authenticatable
  	add_column :clients, :authentication_token, :string
  	add_column :admins, :authentication_token, :string
  	add_column :vendeurs, :authentication_token, :string

  end
end
