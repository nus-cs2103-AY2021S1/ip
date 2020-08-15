package main.java;

import java.util.Scanner;

public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String line = "_____________________________________________________________________\n";

    public void mainProgram() {
        Scanner sc = new Scanner(System.in);
        System.out.println(line + "Hello! I'm Duke.\nWhat can I do for you?\n" + line);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye") || input.equals("Bye")) {
                bye();
                break;
            } else {
                echo(input);
            }
        }
    }

    private void echo(String input) {
        System.out.println(line + input + "\n" + line);
    }

    public void bye() {
        System.out.println(line + "Bye.Hope to see you again soon!\n" + line);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello from\n" + logo);
        duke.mainProgram();
    }
}
