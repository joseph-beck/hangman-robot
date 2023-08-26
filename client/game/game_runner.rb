# frozen_string_literal: true

require './config'
require './game/game'

# GameRunner abstracts any clutter in the main file and contains a singular static method.
# This method can be called without creating an instance of GameRunner.
#
# @author Joseph Beck
# @!visibility public
class GameRunner

  public

  # The static method, run, creates the instance of config and gets the ip and port for the server.
  # It then starts running the game loop.
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def self.run
    config = Config.new
    ip, port = config.ip, config.port
    name = gets

    client = Game.new(ip, port, name)
    client.run
    nil
  end
end
