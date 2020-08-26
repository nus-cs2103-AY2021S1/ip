package duke.input;

import java.util.Scanner;

public class UserInput {

    public static Scanner scanner = new Scanner(System.in);

    public static String getOneLine() {
        String inputLine = scanner.nextLine();
        return inputLine;
    }
}
