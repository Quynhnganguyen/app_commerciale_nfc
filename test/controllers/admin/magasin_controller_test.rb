require 'test_helper'

class Admin::MagasinControllerTest < ActionController::TestCase
  test "should get index" do
    get :index
    assert_response :success
  end

end
