# frozen_string_literal: true

# Config class is used to store variables that easily changeable
# In this case just the ip and port
# @!visibility public
class Config

  # Allows the values of ip and port to be accessed outside of the class itself
  #
  # @!attribute :ip, :port
  # @!visibility public
  attr_accessor :ip, :port

  # Initializes the values of both ip and port
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def initialize
    @ip = '10.0.1.4'
    # @ip = '127.0.0.1'
    @port = 6244
  end
end
