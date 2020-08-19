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
        String separation_line = "    ____________________________________________________________\n";
        String indent = "     ";
        String starting_line = separation_line + indent;
        String ending_line = "\n" + separation_line;
        String greeting = starting_line + "Hello! I'm Duke\n" + indent + "What can I do for you?" + ending_line;
        System.out.println(greeting);

        boolean exit_bye = false;
        while (!exit_bye) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(starting_line + "Bye. Hope to see you again soon!" +  ending_line);
                exit_bye = true;
            } else {
                System.out.println(starting_line + input + ending_line);
            }
        }
    }
}
