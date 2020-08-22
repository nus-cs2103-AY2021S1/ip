package main.java.farrell.duke;

import java.util.Scanner;

public class UiManager {
    final String logo =
            " _\n" +
            "//\\\n" +
            "V  \\\n" +
            " \\  \\_\n" +
            "  \\,'.`-.\n" +
            "   |\\ `. `.\n" +
            "   ( \\  `. `-.                        _,.-:\\\n" +
            "    \\ \\   `.  `-._             __..--' ,-';/\n" +
            "     \\ `.   `-.   `-..___..---'   _.--' ,'/\n" +
            "      `. `.    `-._        __..--'    ,' /\n" +
            "        `. `-_     ``--..''       _.-' ,'\n" +
            "          `-_ `-.___        __,--'   ,'\n" +
            "             `-.__  `----\"\"\"    __.-'\n" +
            "                   `--..____..--'";

    Scanner scanner = new Scanner(System.in);

    public boolean hasUserInput() {
        return scanner.hasNextLine();
    }

    public String getNextInput() {
        return scanner.nextLine();
    }

    public void displayStartMessage() {
        System.out.println(logo);
        printInWindow("Hello, I'm a banana.\nWhat can I do for you?");
    }

    public void printInWindow(String text) {
        String divider = "---------------------------------------------";
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider);
    }
}
