class FranchisetoMagasins < ActiveRecord::Migration
  def change
  	remove_column :franchises, :magasin_id
  end
end
