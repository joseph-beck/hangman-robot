# frozen_string_literal: true

require './client/packet'
require './jade/jade_pair'
require './jade/jade_exceptions'

# The JadeObject is a an object that stores an array of JadePairs.
# JadeObjects can be serialized to strings and files making.
# They can also be deserialized from strings and files.
# Additionally if the JadeObject contains the correct parameters it can be converted to another object.
# In this case JadeObject can be converted to object type Packet.
#
# @author Joseph Beck
# @!visibility public
class JadeObject

  public

  # Args are used outside of the object itself require an attr accessor.
  #
  # @!attribute :args
  # @!visibility public
  attr_accessor :args

  # The initializer of the JadeObject just takes in an array of JadePairs.
  # JadePairs are essential to the functionality of the JadeObject.
  #
  # @param [JadePair[]]
  # @return [NilClass]\
  # @!visibility public
  def initialize(args)
    @args = args
  end

  # to_packet constructs a packet with args as long as args are valid for a packet conversion.
  # Being able to convert a JadeObject to an object of type Packet allows for great flexibility within the program.
  #
  # @param [NilClass]
  # @return [Packet or NilClass]
  # @!visibility public
  def to_packet
    make_packet(@args) if valid_packet_conversion?
  end

  # to_str converts the current JadeObject to a string.
  # This provides an easy way to view the current state of a JadeObject.
  #
  # @param [String]
  # @return [NilClass]
  # @!visibility public
  def to_str
    output = ''

    @args.each_with_index do |arg, index|
      output += arg.to_str
      output += "\n" if index != @args.length - 1
    end
    output
  end

  # to_array converts the current instance of the JadeObject to an array.
  # The array is built of each arg of the JadeObject converted to a string.
  #
  # @param [NilClass]
  # @return [String[]]
  # @!visibility public
  def to_array
    args_values = []
    @args.each do |arg|
      args_values << arg.to_str
    end
  end

  # Checks whether the current args of this instance of the JadeObject is nil?.
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def empty?
    @args.nil?
  end

  private

  # Make packet constructs an object of type Packet from supplied args.
  # This method is called from the to_packet method.
  #
  # @param [JadePair[]]
  # @return [Packet]
  # @!visibility public
  def make_packet(args)
    sender = args[0].second
    reciever = args[1].second
    type = args[2].second
    data = args[3].second
    check_sum = args[4].second
    Packet.new(sender, reciever, type, data, check_sum)
  end

  # Checks whether the current instance of the JadeObject.
  # It does this by checking the equality of the first of the item of args.
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def valid_packet_conversion?
    return false unless @args.length.eql? 5

    ((@args[0].first.eql? 'sender') &&
     (@args[1].first.eql? 'reciever') &&
     (@args[2].first.eql? 'type') &&
     (@args[3].first.eql? 'data') &&
     (@args[4].first.eql? 'checkSum'))
  end
end
