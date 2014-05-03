class CreateFranchises < ActiveRecord::Migration
  def change
    create_table :franchises do |t|

      t.timestamps
    end
  end
end
