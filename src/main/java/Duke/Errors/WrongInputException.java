package Duke.Errors;

import Duke.Errors.DukeException;

/**
 * This prints the exception when a wrong word is being input.
 */
public class WrongInputException extends DukeException {
    /**
     * This takes not args and overrides the toString() method
     * @return a string that describes the error that took place which is that an invalid string has been inserted
     */
    public String toString(){
        return "  '\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}