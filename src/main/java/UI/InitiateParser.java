package UI;

import Errors.ErrorExceptions;
import Parser.InputManager;

public class InitiateParser {
    public static void parser(String input){
        try {
            InputManager.parse(input);
        } catch(ErrorExceptions e){
            System.out.println(e);
        }
    }
}
