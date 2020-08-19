package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);

        boolean exit_bye = false;
        while (!exit_bye) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                exit_bye = true;
            } else {
                System.out.println(input);
            }
        }
    }
}
