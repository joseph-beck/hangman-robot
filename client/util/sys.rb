# frozen_string_literal: true

# System is used to exit among other things.
#
# @author Joseph Beck
# @!visibility public
class Sys

  # Exits the program with a certain status.
  #
  # @param [Integer]
  # @return [NilClass]
  # @!visibility public
  def self.exit_p(status)
    begin
      exit status
    rescue SystemExit
      p 'exiting'
    end
  end
end