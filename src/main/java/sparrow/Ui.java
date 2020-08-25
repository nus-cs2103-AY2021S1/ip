package sparrow;

import java.util.Scanner;

/**
 * Responsible for sending messages to the user.
 */
public class Ui {

    /**
     * Welcomes the user to the program.
     */
    public static void greet() {
        String welcome = "  _  _ _   ___ _                    \n" +
                " | || (_) |_ _( )_ __               \n" +
                " | __ | |  | ||/| '  \\              \n" +
                " |_||_|_| |___| |_|_|_|             \n" +
                " / __|_ __  __ _ _ _ _ _ _____ __ __\n" +
                " \\__ \\ '_ \\/ _` | '_| '_/ _ \\ V  V /\n" +
                " |___/ .__/\\__,_|_| |_| \\___/\\_/\\_/ \n" +
                "     |_|                            ";
        System.out.println(welcome);
        reply("How can I help ye?");
    }

    /**
     * Sends a reply to the user in a standard format.
     * @param message Reply to be formatted.
     */
    public static void reply(String message) {
        System.out.println("    ________________________________________");
        Scanner sc = new Scanner(message);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println("      " + line);
        }
        System.out.println("    ________________________________________");
        sc.close();
    }
}
