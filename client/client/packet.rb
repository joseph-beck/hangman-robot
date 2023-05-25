# frozen_string_literal: true

require './client/packet_exceptions'
require './jade/jade_pair'
require './jade/jade_object'

# The Packet object stores various pieces of information regarding both sent and recieved packets.
#
# @author Joseph Beck
# @!visibility public
class Packet

  public

  # These variables are assigned attr_accessor so that they can be accessed outside the object.
  # They are needed in many different places.
  #
  # @!attribute :sender, :reciever, :type, :data
  # @!visibility public
  attr_accessor :sender, :reciever, :type, :data

  # The initialize method takes in all arguments of the packet and can default the check sum to 1.
  #
  # @param [String, String, String, String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(sender, reciever, type, data, check_sum = '1')
    @sender = sender
    @reciever = reciever
    @type = type
    @data = data
    @check_sum = check_sum
  end

  # The to_array method returns the instance of the Packet as an array.
  #
  # @param [NilClass]
  # @return [Array]
  # @!visibility public
  def to_array
    [@sender, @reciever, @type, @data, @check_sum]
  end

  # This method converts a Packet to a JadeObject.
  # It creates JadePairs of each variable within the packet.
  # A JadeObject is then created from an array of the JadePairs.
  #
  # @param [NilClass]
  # @return [JadeObject]
  # @!visibility public
  def to_jade_object
    sender_pair = JadePair.new('sender', @sender)
    reciever_pair = JadePair.new('reciever', @reciever)
    type_pair = JadePair.new('type', @type)
    data_pair = JadePair.new('data', @data)
    check_sum_pair = JadePair.new('checkSum', @check_sum)
    JadeObject.new([sender_pair, reciever_pair, type_pair, data_pair, check_sum_pair])
  end

  # The to_str method just returns the instance of the Packet as a simple string with no formatting.
  #
  # @param [NilClass]
  # @return [String]
  # @!visibility public
  def to_str
    "#{@sender}, #{@reciever}, #{@type}, #{@data}, #{@check_sum}"
  end

  # empty? checks whether the instance of the packet is empty.
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def empty?
    !@sender.nil? && !@reciever.nil? && !@type.nil? && !@data.nil? && !@check_sum.nil?
  end

  # check_packet? checks the validity of the packet via the check sum.
  # @todo: fully implement the check sum system
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def check_packet?
    @check_sum == '1'
  end

  # pertains_to_user? checks whether the recieved packet pertains to this client.
  #
  # @param [String]
  # @return [Boolean]
  # @!visibility public
  def pertains_to_user?(username)
    ((@reciever == username) ||
     (@reciever == 'all'))
  end
end
