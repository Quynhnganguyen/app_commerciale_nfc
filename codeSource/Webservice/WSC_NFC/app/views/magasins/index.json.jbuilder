json.array!(@magasins) do |magasin|
  json.extract! magasin, :id, :nomMagasin, :adresse, :id_franchise
  json.url magasin_url(magasin, format: :json)
end
