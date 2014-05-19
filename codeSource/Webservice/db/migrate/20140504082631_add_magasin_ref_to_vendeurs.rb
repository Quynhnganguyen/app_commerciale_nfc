class AddMagasinRefToVendeurs < ActiveRecord::Migration
  def change
    add_reference :vendeurs, :magasin, index: true
  end
end
