# frozen_string_literal: true

# An error
#
# @author Joseph Beck
# @!visibility public
class Err

  # Call an error
  #
  # @param [Err]
  # @return [NilClass]
  # @!visibility public
  def self.err(error)
    puts error
  end
end