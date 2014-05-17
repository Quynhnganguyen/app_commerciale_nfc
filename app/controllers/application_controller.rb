class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :null_session
<<<<<<< HEAD
=======
#   before_action :configure_permitted_parameters, if: :devise_controller?

#   protected
#   def configure_permitted_parameters
#    devise_parameter_sanitizer.for(:sign_up) { |u| u.permit(:name, :email, :password, :password_confirmation) }
# end
>>>>>>> aa896e7bcd533ce4fae7d083007b2068db93c436

end
