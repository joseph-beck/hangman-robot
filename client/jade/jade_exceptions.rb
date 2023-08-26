# frozen_string_literal: true

# A generic Jade Exception of type JadeException
# Thrown when Jade does not have a specific instance of the error
#
# @author Joseph Beck
# @!visibility public
class JadeException < StandardError

  public

  # The exception type may require use outside of the instance of itself and therefore has an attribute accessor.
  #
  # @!attribute :exception_type
  # @!visibility public
  attr_accessor :exception_type

  # Overloads the original initialize method of standard error.
  # Initializes the superclass using the message from the parameter.
  # This helps to provide a more specific exception type.
  #
  # @overload initialize
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(msg = 'Jade exception occurred', exception_type = 'JadeException')
    @exception_type = exception_type
    super(msg)
  end
end

# A specialised Jade Exception of type JadeConversionException.
# This is used when trying to convert a JadeObject and an error occurs.
#
# @author Joseph Beck
# @!visibility public
class JadeConversionException < StandardError

  public

  # The exception type may require use outside of the instance of itself and therefore has an attribute accessor.
  #
  # @!attribute :exception_type
  # @!visibility public
  attr_accessor :exception_type

  # Overloads the original initialize method of standard error.
  # Initializes the superclass using the message from the parameter.
  # This helps to provide a more specific exception type.
  #
  # @overload initialize
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(msg = 'Failed to convert JadeObject to other Object', exception_type = 'JadeObjectConversionException')
    @exception_type = exception_type
    super(msg)
  end
end

# A specialised Jade Exception of type JadeSerializationException.
# This is used when trying to serialize a JadeObject to string or file and an error occurs.
#
# @author Joseph Beck
# @!visibility public
class JadeSerializationException < StandardError

  public

  # The exception type may require use outside of the instance of itself and therefore has an attribute accessor.
  #
  # @!attribute :exception_type
  # @!visibility public
  attr_accessor :exception_type

  # Overloads the original initialize method of standard error.
  # Initializes the superclass using the message from the parameter.
  # This helps to provide a more specific exception type.
  #
  # @overload initialize
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(msg = 'Failed to convert JadeObject to other Object', exception_type = 'JadeSerializationException')
    @exception_type = exception_type
    super(msg)
  end
end

# A specialised Jade Exception of type JadeSerializationException.
# This is used when trying to deserialize a JadeObject from string or file and an error occurs.
#
# @author Joseph Beck
# @!visibility public
class JadeDeserializationException < StandardError

  public

  # The exception type may require use outside of the instance of itself and therefore has an attribute accessor.
  #
  # @!attribute :exception_type
  # @!visibility public
  attr_accessor :exception_type

  # Overloads the original initialize method of standard error.
  # Initializes the superclass using the message from the parameter.
  # This helps to provide a more specific exception type.
  #
  # @overload initialize
  # @param [String, String]
  # @return [NilClass]
  # @!visibility public
  def initialize(msg = 'Failed to deserialize to JadeObject', exception_type = 'JadeDeserializationException')
    @exception_type = exception_type
    super(msg)
  end
end
