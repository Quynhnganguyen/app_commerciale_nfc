class DestroyNfcs < ActiveRecord::Migration
  def change
  	drop_table :nfcs
  end
end
