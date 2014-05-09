class Api::ClientsController < ApplicationController
	def sign_in
    @client = Client.where(email: params[:email]).first
    if @client.blank?
      render json: { status: 'Invalid client', message: "Invalid client"}
    elsif @client.valid_password?(params[:password])
      render json: { status: "success", message: "Login success", clientname: @client.name, 
                     client_id: @client.id}
    else
      @client = nil
      render json: { status: 'Invalid password', message: "Invalid password"}
    end
  end

  def sign_out
    current_client.reset_authentication_token!
    render text: 'Logout success'
  end
end
