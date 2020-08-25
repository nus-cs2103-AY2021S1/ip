package duke;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "----------------------------------------";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String text = "  ______  _____  __  ____     __     _______      \n" +
                " |__  __||  __ | | \\ |  |    /_ \\    |   ___|     \n" +
                "    | |  | | | | |  \\|  |   //_\\ \\   |  |___          \n" +
                " _  | |  | | | | | |\\   |  / ____ \\  |____  |   \n" +
                "| |_| |  | |_| | | | \\  | / /    \\ \\ _____| |         \n" +
                "|_____|  |_____| |_|  \\_|/_/      \\_\\|______|       \n" +
                "                  _____   ______  ________                            \n" +
                "                  |  _ \\ |  _  | |__   __|                  \n" +
                "                  | |_| || | | |    | |                      \n" +
                "                  |    / | | | |    | |         \n" +
                "                  |  _ \\ | |_| |    | |       \n" +
                "                  | |_| ||     |    | |         \n" +
                "                  |_____/|_____|    |_|                 \n";

        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                text +
                "  \n  I am a task manager bot that will keep track of all your tasks. \n" +
                "  \n  To view a list of all my commands, input '/commands' \n" +
                "  \n  Now that you are familiar with the commands, how may I assist you today?";

        this.showLine();
        System.out.println(greeting);
        this.showLine();
    }

    public String readCommand() {
        String message = sc.nextLine();
        return message;
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void printReply(String message) {
        System.out.println(message);
    }

    public void end() {
        String farewellMessage = "  GoodBye and I hope to see you soon! Have a fantastic day! ";
        System.out.println(farewellMessage);
        sc.close();
    }

    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }
}
