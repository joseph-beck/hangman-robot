# frozen_string_literal: true

require './jade/jade_pair'
require './jade/jade_object'

# The JadeDeserializer converts either a string or .jde file that is formatted correctly to a JadeObject.
# It does this by parsing the data and creating a series of JadePairs which are used as the arguments of the JadeObject.
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
class JadeDeserializer

  public

  # The from_str method creates a JadeObject from a given string, as long as it able to be converted to JadeObject.
  # It splits it into pairs with .split(';').
  # All new lines are also removed at this point.
  # The pairs are then parsed in another function.
  #
  # @param [String]
  # @return [JadeObject]
  # @!visibility public
  def from_str(jaded_str)
    data = jaded_str.tr("\n", '').split(';')
    make_jade_object(data)
  end

  # The from_file method creates a JadeObject from a given file directory, as long as it able to be converted to JadeObject.
  # It first reads in the file and then splits it into pairs with .split(';').
  # All new line are also removed at this point.
  # The pairs are then parsed in another function.
  #
  # @param [String]
  # @return [JadeObject]
  # @!visibility public
  def from_file(dir)
    file = File.open(dir)
    data = file.read.tr("\n", '').split(';')
    make_jade_object(data)
  end

  private

  # The make_jade_object takes in a half-parsed array of strings and coverts these into JadePairs.
  # It does this for every piece of data in the array.
  # During this all whitespace is removed and it is split but : to get each pair.
  # Each created pair is appended to the jade_pairs array.
  # This array is later used as args for the new JadeObject which is returned.
  #
  # @param [String[]]
  # @return [JadeObject]
  # @!visibility public
  def make_jade_object(data)
    jade_pairs = []

    data.each do |piece|
      piece = piece.tr(' ', '').tr("'", '')
      first, second = piece.split(':')
      first = first.tr(' ', '').tr("\n", '')
      second = second.tr(' ', '').tr("\n", '')
      jade_pairs << JadePair.new(first, second)
    end
    JadeObject.new(jade_pairs)
  end
end
