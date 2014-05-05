class Admin::MagasinController < ApplicationController

  # def index
  #   @admin = admin
  # 	@magasins = Magasin.all
  # end

  # def new
  # 	@magasin = Magasin.new

  # 	respond_to do |format|
  #   format.html  # new.html.erb
  #   format.json  { render :json => @post }

  #   end
  # end

  # def create
  # 	 @magasin = Magasin.new(magasin_params)

	 # if @magasin.save
  #           redirect_to :action => 'index'
  #     else
  #           render :action => 'new'
  #     end
  # end

  # def list
  # 	@magasins = Magasin.find(:all)
  # end

  # private

  # def admin
  #   if current_admin.id
  #     id = current_admin.id
  #     Admin.find(current_admin.id)
  #   end
  # end

  # def magasin_params
  #   params.require(:magasin).permit(:nom_magasin, :adresse_magasin)
  # end

  def index
    @context = context
    @magasins = Magasin.all
  end
  
  def new
    @context = context
    @magasin = @context.magasin.new
  end
 
  def create
    @context = context
    @magasin = @context.magasin.new(magasin_params)
 
    if @magasin.save
      redirect_to context_url(context), notice: "The magasin has been successfully created."
    end
  end

  def edit
    @context = context
    @magasin = @context.magasin.find(params[:id])
  end
 
  def update
    @context = context
    @magasin = @context.magasin.find(params[:id])
    if @magasin.update_attributes(magasin_params)
      redirect_to context_url(context), notice: "The magasin has been updated"
    end
  end
 
private

  def magasin_params
    params.require(:magasin).permit!
  end
 
  def context
    if params[:franchise_id]
      id = params[:franchise_id]
      Franchise.find(params[:franchise_id])
    end
  end 

  def context_url(context)
    if Franchise === context
      admin_magasin_index_path(context)
    end
  end
end
