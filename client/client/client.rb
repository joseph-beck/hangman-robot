# frozen_string_literal: true

require 'socket'

require './client/packet'
require './jade/jade_serializer'
require './jade/jade_deserializer'
require './jade/jade_object'


# This class is used connect and run a client that connects using a TCP Socket.
#
# @author Joseph Beck
# @!visibility public
class Client

  public

  # The initialize method assigns all variables, both username and reciever and not required and default to ruby and all respectively.
  # During this the Socket is also initialized.
  #
  # @param [String, Integer, String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(ip, port, username = 'ruby', reciever = 'all')
    @socket = Socket.new Socket::AF_INET, Socket::SOCK_STREAM

    @ip = ip
    @port = port
    @username = username
    @reciever = reciever

    @serializer = JadeSerializer.new
    @deserializer = JadeDeserializer.new

    @threads = Array.new
  end

  protected

  # Connects the socket to the early initialized port and ip.
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def connect
    @socket.connect Socket.pack_sockaddr_in(@port, @ip)
    nil
  end

  # Runs the client of the server.
  # Starts by sending the clients username to the server, this is required by the server.
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def run
    connect
    @socket.puts @username

    make_threads
    @threads.each(&:join)
    nil
  end

  # Close allows for the socket to be closed.
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def close
    exit_threads
    @socket.close_read
    @socket.close_write
    @socket.close
    nil
  end

  # Makes an array of threads to run.
  #
  # @param [NilClass]
  # @return [ThreadGroup]
  # @!visibility public
  def make_threads
    @threads << recieve_thread
    @threads << send_thread
  end

  # Creates the recieve thread, this listens to the server for traffic.
  # If there is a message sent to the server it is converted to a JadeObject.
  # This is done via the JadeDeserializer.
  # The JadeObject is then converted to a packet and its data is put to the console.
  #
  # @param [NilClass]
  # @return [Thread]
  # @!visibility public
  def recieve_thread
    Thread.new {
      until @socket.closed?
        output = @socket.gets
        jade_object = @deserializer.from_str(output)
        packet = jade_object.to_packet
        puts packet.to_str if packet.pertains_to_user?(@username)
      end
    }
  end

  # Creates the sender thread, this waits for a user input.
  # When there is a user input it is converted to a Packet.
  # The Packet is then converted to a JadeObject.
  # The JadeObject is then serialized to a string and sent to the server.
  #
  # @param [NilClass]
  # @return [Thread]
  # @!visibility public
  def send_thread
    Thread.new {
      until @socket.closed?
        input = gets
        packet = Packet.new(@username, @reciever, 'data', input)
        jade_object = packet.to_jade_object
        jade_string = @serializer.to_str(jade_object)
        @socket.puts jade_string
      end
    }
  end

  # Exits the threads
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def exit_threads
    @threads.each(&:exit)
  end
end
