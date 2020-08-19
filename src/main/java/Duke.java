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

        Task[] task_arr = new Task[100];
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
                System.out.println(separation_line);
                if (input.equals("list")) {
                    int temp = 1;
                    System.out.println(indent + "Here are the tasks in your list:");
                    while (task_arr[temp - 1] != null) {
                        System.out.println(indent + temp + ".[" + task_arr[temp - 1].getStatusIcon() + "] " + task_arr[temp - 1].getDescription());
                        temp++;
                    }
                } else if (input.substring(0, 4).equals("done")) {
                    int done_number = Integer.parseInt(input.substring(5, input.length()));
                    task_arr[count - 1].markAsDone();
                    System.out.println(indent + "Nice! I've marked this task as done:");
                    System.out.println(indent + "  [\u2713] " + task_arr[count - 1].getDescription());
                } else {
                    System.out.println(indent + "added: " + input);
                    task_arr[count] = new Task(input);
                    count++;
                }
                System.out.println(separation_line + "\n");
                //System.out.println(starting_line + input + ending_line);
            }
        }
    }
}
