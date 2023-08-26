# frozen_string_literal: true

require './game/game_runner'
require './game/game_states'

# Entry point of the program.
# Runs the GameRunner static method run.
#
# @author Joseph Beck
# @!visibility public
if __FILE__ == $PROGRAM_NAME
  GameRunner.run
end
