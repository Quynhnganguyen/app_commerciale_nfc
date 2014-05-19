class CreateFranchises < ActiveRecord::Migration
  def change
    create_table :franchises do |t|
    	t.string :nom_entreprise,     null: false
      t.timestamps
    end
  end
end
