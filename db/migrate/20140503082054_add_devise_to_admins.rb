class AddDeviseToAdmins < ActiveRecord::Migration
  def self.up
    change_table(:admins) do |t|
      ## Database authenticatable
      add_column :admins, :email, :string, null: false, default: ""
      add_column :admins, :encrypted_password, :string, null: false, default: ""
      

      ## Recoverable
      add_column :admins, :reset_password_token, :string
      add_column :admins, :reset_password_sent_at, :datetime

      ## Rememberable
      add_column :admins, :remember_created_at, :datetime

      ## Trackable
      add_column :admins, :sign_in_count, :integer, default: 0, null: false
      add_column :admins, :current_sign_in_at, :datetime
      add_column :admins, :last_sign_in_at, :datetime
      add_column :admins, :current_sign_in_ip, :string
      add_column :admins, :last_sign_in_ip, :string

      ## Confirmable
      # t.string   :confirmation_token
      # t.datetime :confirmed_at
      # t.datetime :confirmation_sent_at
      # t.string   :unconfirmed_email # Only if using reconfirmable

      ## Lockable
      # t.integer  :failed_attempts, default: 0, null: false # Only if lock strategy is :failed_attempts
      # t.string   :unlock_token # Only if unlock strategy is :email or :both
      # t.datetime :locked_at


      # Uncomment below if timestamps were not included in your original model.
      # t.timestamps
    end

    add_index :admins, :email,                unique: true
    add_index :admins, :reset_password_token, unique: true
    # add_index :admins, :confirmation_token,   unique: true
    # add_index :admins, :unlock_token,         unique: true
  end

  def self.down
    # By default, we don't want to make any assumption about how to roll back a migration when your
    # model already existed. Please edit below which fields you would like to remove in this migration.
    raise ActiveRecord::IrreversibleMigration
  end
end
