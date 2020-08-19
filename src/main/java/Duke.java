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
        String separation_line = "    ____________________________________________________________";
        String indent = "     ";
        String starting_line = separation_line + "\n" + indent;
        String ending_line = "\n" + separation_line + "\n";

        String[] task_arr = new String[100];
        int count = 0;

        String greeting = starting_line + "Hello! This is J.A.R.V.I.S.\n" + indent + "How may I help you?" + ending_line;
        System.out.println(greeting);

        boolean exit_bye = false;
        while (!exit_bye) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(starting_line + "Bye. Hope to see you again soon!" +  ending_line);
                exit_bye = true;
            } else {
                if (input.equals("list")) {
                    int temp = 1;
                    System.out.println(separation_line);
                    while (task_arr[temp - 1] != null) {
                        System.out.println(indent + temp + ". " + task_arr[temp - 1]);
                        temp++;
                    }
                    System.out.println(separation_line + "\n");
                } else {
                    System.out.println(starting_line + "added: " + input + ending_line);
                    task_arr[count] = input;
                    count++;
                }
                //System.out.println(starting_line + input + ending_line);
            }
        }
    }
}
