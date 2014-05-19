class CreateSources < ActiveRecord::Migration
  def change
    create_table :sources do |t|
    	t.string :pays,  null: false
      t.timestamps
    end
  end
end
