/**
 * An exception that is reported when the Bot.java detects no descriptions in the user input.
 */

public class NoDescriptionException extends Exception {

    String s;

    public NoDescriptionException(String s) {
        this.s = s;
    }

}
