package main.java;

import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static final String TERMINATE_COMMAND = "bye";
    static final String LINE = "    _____________________________________________________________________\n";
    static final String SINGLE_TAB = "  ";
    static final String DOUBLE_TAB = "      ";

    public void mainProgram() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals(TERMINATE_COMMAND)) {
                bye();
                break;
            } else {
                echo(input);
            }
        }
    }

    private void greet() {
        System.out.println(LINE + DOUBLE_TAB + "Hello! I'm Rich.\n" + DOUBLE_TAB  + "What can I do for you?\n" + LINE);
    }

    private void echo(String input) {
        System.out.println(LINE + DOUBLE_TAB + input + "\n" + LINE);
    }

    public void bye() {
        System.out.println(LINE + DOUBLE_TAB +"Bye.Hope to see you again soon!\n" + LINE);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello from\n" + logo);
        duke.mainProgram();
    }
}
