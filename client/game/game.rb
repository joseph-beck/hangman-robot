# frozen_string_literal: true

require 'socket'
require 'thread'

require './client/packet'
require './client/client'
require './jade/jade_serializer'
require './jade/jade_deserializer'
require './jade/jade_object'
require './game/game_states'

# The game class stores the basic client side logic of hangman.
# It inherits from client in order to provide greater code clarity.
#
# @author Joseph Beck
# @!visibility public
class Game < Client

  public

  # The initializer of Game takes in a variety of params, all are used to initialize the superclass.
  # guesses and lives_left are initialized to their starting states.
  #
  # @param [String, Integer, String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(ip, port, username = 'ruby', reciever = 'all')
    super(ip, port, username, reciever)

    @state = GameStates.new(1)
    @guessing = false
    @guesses = []
    @lives_left = 7
  end

  # The run method overrides the superclasses run however uses super to gain most of its functionality.
  #
  # @overload run
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def run
    puts 'Now playing hangman...'
    super
  end

  # Puts a disconnecting method and disconnects via the superclass method close.
  # Also exits the program with status 1 (meaning done)
  #
  # @overload close
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def close
    super

    puts 'Disconnected'
    Sys.exit_p 1
    nil
  end

  private

  # Gets a user's guess and ensures that is it valid.
  # Also decrements lives left and adds the users guess to guesses.
  #
  # @param [NilClass]
  # @return [String]
  # @!visibility public
  def guess
    guess = input_guess
    until valid_guess?(guess)
      puts 'Invalid input, try again'
      guess = input_guess
    end
    @lives_left -= 1
    @guesses << guess
    @guessing = false
    guess
  end

  # Waits for the robot to go back into the guessing state.
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def wait
    until @guessing;
      break if @guessing
    end
    input_guess
  end

  # Series of events once the robot sends the finished packet.
  #
  # @param [NilClass]
  # @return [NilClass]
  # @!visibility public
  def finished
    puts 'Game over!'
    close
  end

  # Takes a users guess from the console.
  #
  # @param [NilClass]
  # @return [String]
  # @!visibility public
  def input_guess
    if @guessing
      puts 'Enter guess: '
      gets
    else
      puts 'Waiting for robot...'
      wait
    end
  end

  # The send_thread method overrides the superclasses send_thread method.
  # It extends it further by implementing the user guess system.
  # The guess is also validated.
  #
  # @overload send_thread
  # @param [NilClass]
  # @return [Thread]
  # @!visibility public
  def send_thread
    Thread.new {
      until @socket.closed? || dead?
        input = guess
        packet = Packet.new(@username, @reciever, 'guess', input)
        jade_object = packet.to_jade_object
        jade_string = @serializer.to_str(jade_object)
        @socket.puts jade_string
      end
    }
  end

  # Extended the recieve thread from the client to change the states of the game depending on the recieved packet.
  # This prevents the user from guessing whilst the robot is drawing something.
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

        if packet.type.eql? 'state'
          case packet.data.downcase
          when 'guessing'
            @guessing = true
          when 'finished'
            finished
          else
            @guessing = false
          end
        end
      end
    }
  end

  # Checks if the amount of lives left is zero, if it is then puts game over and returns true.
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def dead?
    # TODO: Implement this
    false
  end

  # Checks if the user inputted guess is valid.
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def valid_guess?(guess)
    return false if guess.nil?

    (guess.length == 2 &&
     guess.match?(/[[:alpha:]]/) &&
     !already_guessed?(guess))
  end

  # Checks if the users guess has already been guessed.
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def already_guessed?(guess)
    @guesses.include? guess
  end
end
