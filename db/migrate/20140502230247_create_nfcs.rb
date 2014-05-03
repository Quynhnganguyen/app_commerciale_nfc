class CreateNfcs < ActiveRecord::Migration
  def change
    create_table :nfcs do |t|
    	t.string :id_nfc_tag,     null: false
      t.timestamps
    end
  end
end
