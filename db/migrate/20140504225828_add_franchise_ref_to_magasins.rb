class AddFranchiseRefToMagasins < ActiveRecord::Migration
  def change
    add_reference :magasins, :franchise, index: true
  end
end
