/**
 * Inherits from the DukeException class and is thrown
 * when the command words are not used.
 */
public class WrongCommandException extends DukeException {

     public WrongCommandException() {
            super("Sorry, I did not understand that. Please use the correct command words.");
     }
}
