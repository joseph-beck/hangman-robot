package drawing.other.symbols;

import drawing.Marker;
import drawing.other.Symbol;
import drawing.other.Symbols;


/**
 * This draws out underscores for the Hangman game and implements the symbol interface.
 * @author Natan Wojcik
 * @version 0.1
 * @since 2023 
 */
public class UnderScore implements Symbol {
    /**
     * Creates a symbols object.
     */
    private Symbols symbol;

    /**
     * Constructor which assigns 'symbol' to an Enum.
     * 
     * @return none
     */
    public UnderScore() {
        symbol = Symbols.UNDER_SCORE;
    }

    /**
     * This draws out an underscore.
     * 
     * @return none
     */
    @Override
    public void draw() {
        draw(1);
    }

    /**
     * This takes in the an integer 'amount' which is used to draw out that number of underscores.
     * 
     * @param int amount 
     * @return none
     */
    @Override
    public void draw(int amount) {
        Marker.calibrate();

    	for(int i = 0; i < amount; i++) {
    		Marker.markerDown();
    		Marker.markerHorizontal(-300);
    		Marker.markerUp();
    		Marker.markerHorizontal(-75);
    	}
    }
    /**
     * Returns the enum.
     * 
     * @return Symbols
     */
    public Symbols getSymbol() {
        return this.symbol;
    }
}