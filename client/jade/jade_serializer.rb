# frozen_string_literal: true

require './jade/jade_object'
require './jade/jade_pair'
require './client/packet'

# The JadeSerializer is used in order to convert a JadeObject to a string.
# Once it has been serialized into a string it can be saved to a file or sent as text.
#
# Formatting (with pretty printing):
#
# <variableOne>: '<valueOne>';
#
# <variableTwo>: '<valueTwo>';
#
# Formatting (without pretty printing):
#
# <variableOne>: '<valueOne>'; <variableTwo>: '<valueTwo>';
#
# : -> Splits the variable and its value
#
# ; -> Denotes an end of line
#
# ' -> Surround the value of the variable
#
# " -> Can also surround the value of the variable
#
# When parsing Jade ignores ALL whitespace, even in variables.
#
# @author Joseph Beck
# @!visibility public
class JadeSerializer

  public

  # The value of pretty printing is set outside of the class itself.
  #
  # @!attribute :pretty_printing
  # @!visibility public
  attr_accessor :pretty_printing

  # Initializes the value of pretty printing to false by default.
  # This can be changed at runtime or set via the initializer.
  #
  # @param [Boolean]
  # @return [NilClass]
  # @!visibility public
  def initialize(pretty_printing = false)
    @pretty_printing = pretty_printing
  end

  # to_str converts a given JadeObject to a string.
  #
  # @param [JadeObject]
  # @return [String]
  # @!visibility public
  def to_str(jade_object)
    args = jade_object.args
    make_str(args)
  end

  # to_file converts a given JadeObject to a .jde file in a given directory.
  #
  # @param [JadeObject, String]
  # @return [String]
  # @!visibility public
  def to_file(jade_object, dir)
    args = jade_object.args
    output = make_str(args)
    File.open(dir, 'w')
    File.write(dir, output)
    nil
  end

  private

  # make_str takes args, this will be an array of JadePairs.
  # The array of JadePairs is then converted pair by pair into a string.
  # The layout of the string may vary based on the whether pretty printing is on or not.
  #
  # @param [JadePair[]]
  # @return [String]
  # @!visibility public
  def make_str(args)
    output = ''

    args.each_with_index do |arg, index|
      first, second = arg.to_array
      output += "#{first} : '#{second}';"
      output += "\n" if index != args.length - 1 && @pretty_printing
    end
    output.tr("\n", '')
  end
end
