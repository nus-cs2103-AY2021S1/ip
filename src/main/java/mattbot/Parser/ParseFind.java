package mattbot.Parser;

import java.util.NoSuchElementException;
import java.util.Scanner;

import mattbot.Command.FindCommand;
import mattbot.Errors.ErrorExceptions;

/**
 * Represents the bridging class that connects to the find Command.
 */
public class ParseFind extends Parse {
    /**
     * Takes the user input and breaks down into the search keywords and pass it to the
     * find Command.
     * Returns the filtered message.
     *
     * @param input
     * @return String filtered message.
     * @throws ErrorExceptions
     */
    public static String execute2(String input) throws ErrorExceptions {
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
            return FindCommand.execute2(name);
        } catch (NoSuchElementException e) {
            throw new ErrorExceptions("Wrong format! Missing name!");
        }
    }
}
