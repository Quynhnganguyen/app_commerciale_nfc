WsCommercial::Application.routes.draw do
  mount RailsAdmin::Engine => '/admins', as: 'rails_admin'
  # devise_for :admins
  root :to => "home#index"

  devise_for :users, :controllers => {:registrations => "registrations"}
  # devise_for :users, path: "auth", path_names: { sign_in: 'login', sign_out: 'logout', password: 'secret', confirmation: 'verification', unlock: 'unblock', registration: 'register', sign_up: 'cmon_let_me_in' }
  resources :users

  devise_for :admins, :controllers => {:registrations => "registrations"}

  resources :admins
 #  	devise_scope :user do
 # 	 get "sign_in", to: "devise/sessions#new"
 # 	 get "sign_out", to: "devise/sessions#destroy"
	# end
end