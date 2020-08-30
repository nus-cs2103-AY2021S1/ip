package Parser;

import java.util.NoSuchElementException;
import java.util.Scanner;

import Command.FindCommand;
import Errors.ErrorExceptions;

/**
 * Represents the bridging class that connects to the find Command.
 */
public class ParseFind extends Parse {
    /**
     * Takes the user input and breaks down into the search keywords and pass it to the
     * find Command.
     *
     * @param input
     * @throws ErrorExceptions
     */
    public static void execute(String input) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        sc.next();
        String name = "";
        try {
            String current = sc.next();
            while (sc.hasNext()) {
                name = name + current + " ";
                current = sc.next();
            }
            name = name + current + " ";
            FindCommand.execute(name);
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("Wrong format! Missing name!");
        }
    }
}
