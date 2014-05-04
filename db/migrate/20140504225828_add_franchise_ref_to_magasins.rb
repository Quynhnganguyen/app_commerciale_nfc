class AddFranchiseRefToMagasins < ActiveRecord::Migration
  def change
    add_reference :magasins, :franchise, index: true
    remove_column :magasins, :franshise_id
  end
end
