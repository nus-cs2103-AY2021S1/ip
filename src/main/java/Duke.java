package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        TaskList taskList = new TaskList();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                taskList.list();
            } else if (input.equals("")) {
                // Do nothing
            } else {
                String[] inputArray = input.split("\\s+");
                if (inputArray.length >= 2 && inputArray[0].equals("done")) {
                    try {
                        taskList.markAsDone(Integer.parseInt(inputArray[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid task");
                    }
                } else {
                    taskList.add(input);
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Have a nice day.");
    }
}
