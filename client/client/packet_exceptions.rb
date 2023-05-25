# frozen_string_literal: true

# The Packet Conversion Exception inherits from the StandardError.
# It is used when an error occurs converting from an object of type Packet to something else.
#
# @author Joseph Beck
# @!visibility public
class PacketConversionException < StandardError

  public

  # The exception type may require use outside of the instance of itself and therefore has an attribute accessor.
  #
  # @!attribute :exception_type
  # @!visibility public
  attr_accessor :exception_type

  # The constructor of this class takes in a msg and defaults just like the exception type.
  # This then calls the superclass' constructor with.
  #
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(msg = 'Failed to convert Packet to other Object', exception_type = 'PacketConversionException')
    @exception_type = exception_type
    super(msg)
  end
end
