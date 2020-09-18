package duke.input;

import java.util.Scanner;

/**
 * UserInput gets inputs from the key broad.
 */
public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Gets one line entered by the key broad.
     * @return the string of the input line
     */
    public static String getOneLine() {
        String inputLine = scanner.nextLine();
        return inputLine;
    }
}
