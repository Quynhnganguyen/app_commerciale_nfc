class AddMagasinRefToSources < ActiveRecord::Migration
  def change
    add_reference :sources, :magasin, index: true
  end
end
