# frozen_string_literal: true

require '../enum/enum'

# Enum for interpreting the type of a packet
#
# @author Joseph Beck
# @!visibility public
class PacketType < Enum

  # Initializes the states and their values.
  #
  # @!attribute :state, :data, :guess, :finished, :join, :leave, :leave, :exit, :other
  # @!visibility public
  enum_attr :state,     1
  enum_attr :data,      2
  enum_attr :guess,     3
  enum_attr :finished,  4
  enum_attr :join,      5
  enum_attr :leave,     6
  enum_attr :exit,      7
  enum_attr :other,     8
end
