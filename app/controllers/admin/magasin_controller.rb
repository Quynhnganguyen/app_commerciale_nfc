class Admin::MagasinController < ApplicationController

  def index
    @admin = admin
  	@magasins = Magasin.all
  end

  def new
  	@magasin = Magasin.new

  	respond_to do |format|
    format.html  # new.html.erb
    format.json  { render :json => @post }

    end
  end

  def create
  	 @magasin = Magasin.new(magasin_params)

	 if @magasin.save
            redirect_to :action => 'index'
      else
            render :action => 'new'
      end
  end

  def list
  	@magasins = Magasin.find(:all)
  end

  private

  def admin
    if current_admin.id
      id = current_admin.id
      Admin.find(current_admin.id)
    end
  end

  def magasin_params
    params.require(:magasin).permit(:nom_magasin, :adresse_magasin)
  end
end
