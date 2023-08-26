# frozen_string_literal: true

# The JadePair is essential to the JadeObject.
# It stores two stings of first and second.
# Commonly the first is the variable name and the second is the variable data.
# JadePairs are used commonly throughout both the JadeSerializer and JadeDeserializer too.
#
# @author Joseph Beck
# @!visibility public
class JadePair

  public

  # First and second are commonly used outside of the JadePair itself and therefore require an attribute accessor.
  #
  # @!attribute :first, :second
  # @!visibility public
  attr_accessor :first, :second

  # Initializes the instance of both first and second.
  # These default to nil if nothing is provided in the parameters.
  #
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(first = nil, second = nil)
    @first = first
    @second = second
  end

  # Allows both the values of first and second to be replaced.
  #
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def replace(first, second)
    @first = first
    @second = second
    nil
  end

  # Allows the value of first to be replaced.
  #
  # @param [String]
  # @return [NilClass]
  # @!visibility public
  def replace_first(first)
    @first = first
    nil
  end

  # Allows the value of second to be replaced.
  #
  # @param [String]
  # @return [NilClass]
  # @!visibility public
  def replace_second(second)
    @second = second
    nil
  end

  # to_array provides an array of both the values of first and second.
  # This may be useful in some cases of JadeObject construction.
  #
  # @param [NilClass]
  # @return [String[]]
  # @!visibility public
  def to_array
    [@first, @second]
  end

  # to_str converts the current instance of the JadePair to a string.
  # Useful when wanting a simple representation of the current JadePair.
  #
  # @param [NilClass]
  # @return [String]
  # @!visibility public
  def to_str
    "#{@first} : #{@second}"
  end

  # Checks whether the current instance of JadePair is empty
  # Useful for preventing uses of nil JadePairs
  #
  # @param [NilClass]
  # @return [Boolean]
  # @!visibility public
  def empty?
    @first.nil? && @second.nil?
  end

  # Checks whether the current instance of JadePair is equal to another JadePair
  # Useful for checking JadePair equivalence
  #
  # @param [JadePair]
  # @return [Boolean]
  # @!visibility public
  def eql?(other)
    ((@first.eql? other.first) &&
     (@second.eql? other.second))
  end
end
