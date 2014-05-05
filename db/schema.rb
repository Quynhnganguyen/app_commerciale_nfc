# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20140505135838) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "admin_magasins", force: true do |t|
    t.string   "name"
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "admin_magasins", ["email"], name: "index_admin_magasins_on_email", unique: true, using: :btree
  add_index "admin_magasins", ["reset_password_token"], name: "index_admin_magasins_on_reset_password_token", unique: true, using: :btree

  create_table "admins", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "name"
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
  end

  add_index "admins", ["email"], name: "index_admins_on_email", unique: true, using: :btree
  add_index "admins", ["reset_password_token"], name: "index_admins_on_reset_password_token", unique: true, using: :btree

  create_table "clients", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.string   "name"
  end

  add_index "clients", ["email"], name: "index_clients_on_email", unique: true, using: :btree
  add_index "clients", ["reset_password_token"], name: "index_clients_on_reset_password_token", unique: true, using: :btree

  create_table "franchises", force: true do |t|
    t.string   "nom_entreprise", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "liste_acheters", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "liste_coure_deja_faites", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "liste_favoris", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "liste_noires", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "magasin_clients", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "magasins", force: true do |t|
    t.string   "nom_magasin",     null: false
    t.string   "adresse_magasin", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "franchise_id"
  end

  add_index "magasins", ["franchise_id"], name: "index_magasins_on_franchise_id", using: :btree

  create_table "nfcs", force: true do |t|
    t.string   "id_nfc_tag", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "produits", force: true do |t|
    t.string   "nom_produit",        null: false
    t.float    "prix",               null: false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "magasin_id"
    t.integer  "source_id"
    t.integer  "type_de_produit_id"
    t.integer  "nfc_id"
  end

  add_index "produits", ["magasin_id"], name: "index_produits_on_magasin_id", using: :btree
  add_index "produits", ["nfc_id"], name: "index_produits_on_nfc_id", using: :btree
  add_index "produits", ["source_id"], name: "index_produits_on_source_id", using: :btree
  add_index "produits", ["type_de_produit_id"], name: "index_produits_on_type_de_produit_id", using: :btree

  create_table "sources", force: true do |t|
    t.string   "pays",       null: false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "magasin_id"
  end

  add_index "sources", ["magasin_id"], name: "index_sources_on_magasin_id", using: :btree

  create_table "type_de_produits", force: true do |t|
    t.string   "type_produit", null: false
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "magasin_id"
  end

  add_index "type_de_produits", ["magasin_id"], name: "index_type_de_produits_on_magasin_id", using: :btree

  create_table "vendeurs", force: true do |t|
    t.string   "name"
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "magasin_id"
  end

  add_index "vendeurs", ["email"], name: "index_vendeurs_on_email", unique: true, using: :btree
  add_index "vendeurs", ["magasin_id"], name: "index_vendeurs_on_magasin_id", using: :btree
  add_index "vendeurs", ["reset_password_token"], name: "index_vendeurs_on_reset_password_token", unique: true, using: :btree

end
