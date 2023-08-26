# frozen_string_literal: true

# The Enum class allows for better handling of the states within a given scenario.
# This is often inherited when introducing different enums.
#
# @author Joseph Beck
# @!visibility public
class Enum

  private

  # Create an enum attribute and create methods for each attribute.
  # Each attribute can be checked whether it is true for that instance of the attribute.
  # Additionally they can be changed to another type of attribute.
  #
  # @param [String, Integer]
  # @return [NilClass]
  # @!visibility public
  def self.enum_attr(name, num)
    name = name.to_s

    define_method(name + '?') do
      @attrs & num != 0
    end

    define_method(name + '=') do |set|
      if set
        @attrs |= num
      else
        @attrs &= ~num
      end
    end
  end

  public

  # Initialize the given attribute.
  # 0 is assigned if nil args.
  #
  # @param [Integer]
  # @return [NilClass]
  # @!visibility public
  def initialize(attrs = 0)
    @attrs = attrs
  end

  # Convert the value of the attribute on integer.
  #
  # @param [NilClass]
  # @return [Integer]
  # @!visibility public
  def to_i
    @attrs
  end
end