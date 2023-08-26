# frozen_string_literal: true

require './enum/enum'

# GameStates inherits from Enum in order to implement a series of game states.
# This allows for easy handling of branching during the games loop.
#
# @author Joseph Beck
# @!visibility public
class GameStates < Enum

  # Initializes the different potential states of the game.
  # Each is assigned an = method and a ? method for assigning and checking of parity.
  #
  # @!attribute :initializing, :guessing, :waiting, :finished
  # @!visibility public
  enum_attr :initializing,  1
  enum_attr :guessing,      2
  enum_attr :waiting,       3
  enum_attr :finished,      4
end