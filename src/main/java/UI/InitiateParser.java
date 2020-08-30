package UI;

import Errors.ErrorExceptions;
import Parser.InputManager;

/**
 * Represents the bridge between the UserInterface and the Parser.
 */
public class InitiateParser {
    /**
     * Calls the parse method in InputManger, acts as a bridging method.
     * @param input user input.
     */
    public static void parser(String input) {
        try {
            InputManager.parse(input);
        } catch (ErrorExceptions e) {
            System.out.println(e);
        }
    }
}
