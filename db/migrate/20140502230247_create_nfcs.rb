class CreateNfcs < ActiveRecord::Migration
  def change
    create_table :nfcs do |t|

      t.timestamps
    end
  end
end
