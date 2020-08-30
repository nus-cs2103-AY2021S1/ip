package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\n\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n\n");

        String[] store = new String[100];
        boolean[] flag = new boolean[100];
        int count = 0;
        String input = sc.nextLine();

        while (true) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            }

            if (input.equals("done")) {

            }

            if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(Integer.toString(i + 1) + "." + store[i]);
                }
                System.out.println("\n");
                input = sc.nextLine();
            } else {

                System.out.println("\n added: " + input + "\n");
                store[count] = input;
                flag[count] = false;
                count++;
                input = sc.nextLine();
            }
        }
    }
}
