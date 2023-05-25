package drawing.letters;

import java.util.HashMap;

import drawing.letters.alphabet.*;

/**
 * This manages the stages of the alphabet drawings by creating a
 * hashmap of stages which are assigned to an object.
 * 
 * @author Natan Wojcik & Joseph Beck
 * @version 0.1
 * @since 2023 
 */
public class Alphabet {
    /**
     * This creats a HashMap using a Character and Letter object.
     */
    private HashMap<Character, Letter> alphabet;

     /**
     * Constructor to make alphabet.
     * 
     * @return none
     */
    public Alphabet() {
        makeAlphabet();
    }

    /**
     * This assigns all characters in the alphabet to it's corresponding object and puts it within
     * the HashMap
     * 
     * @return none
     */
    private void makeAlphabet() {
        alphabet = new HashMap<Character, Letter>();

        alphabet.put('A', new A());
        alphabet.put('B', new B());
        alphabet.put('C', new C());
        alphabet.put('D', new D());
        alphabet.put('E', new E());
        alphabet.put('F', new F());
        alphabet.put('G', new G());
        alphabet.put('H', new H());
        alphabet.put('I', new I());
        alphabet.put('J', new J());
        alphabet.put('K', new K());
        alphabet.put('L', new L());
        alphabet.put('M', new M());
        alphabet.put('N', new N());
        alphabet.put('O', new O());
        alphabet.put('P', new P());
        alphabet.put('Q', new Q());
        alphabet.put('R', new R());
        alphabet.put('S', new S());
        alphabet.put('T', new T());
        alphabet.put('U', new U());
        alphabet.put('V', new V());
        alphabet.put('W', new W());
        alphabet.put('X', new X());
        alphabet.put('Y', new Y());
        alphabet.put('Z', new Z());
    }

    /**
     * returns HashMap of the alphabet.
     * @return HashMap<Character, Letter> 
     */
    public HashMap<Character, Letter> getAlphabet() {
        return alphabet;
    }
}