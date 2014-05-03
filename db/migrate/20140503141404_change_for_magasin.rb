class ChangeForMagasin < ActiveRecord::Migration
  def change
  	drop_table :admin_magasins
  end
end
