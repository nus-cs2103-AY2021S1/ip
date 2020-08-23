package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String spacer = "               ";
    private static final String logo = spacer + " ____        _        \n"
            + spacer + "|  _ \\ _   _| | _____ \n"
            + spacer + "| | | | | | | |/ / _ \\\n"
            + spacer + "| |_| | |_| |   <  __/\n"
            + spacer + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String OPENING_MSG = " *** Opening and loading relevant documents into duke.Duke ***";
    private static final String GOODBYE_MSG = "Bye ^.^, Hope to see you again soon!!!";
    private static final String HELLO_DUKE = "Hello! I'm duke.Duke ^.^";
    private static final String QUESTION = "What can I do for you?";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greetings() {
        messageFormatter(() -> {
            System.out.println(OPENING_MSG);
            System.out.println(logo);
        });
        messageFormatter(() -> {
            System.out.println(HELLO_DUKE);
            System.out.println(QUESTION);
        });
    }

    public void goodBye() {
        messageFormatter(() -> System.out.println(GOODBYE_MSG));
    }

    public void printException(String msg) {
        messageFormatter(() -> System.out.println(msg));
    }

    // Formatter to format any message. Easily customizable
    public void messageFormatter(Runnable func) {
        System.out.println(LINE);
        func.run();
        System.out.println(LINE);
        System.out.println();
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
    
}
