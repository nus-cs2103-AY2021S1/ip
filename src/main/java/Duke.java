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
        while (true) {
            String[] inputArray = input.split("\\s+");
            switch (inputArray[0]) {
            case "bye":
                System.out.println("Have a nice day.");
                return;
            case "":
                break;
            case "todo":
                String content = input.substring(5);
                taskList.add(new ToDo(content));
                break;
            case "deadline":
                String[] split = input.substring(9).split("\\s+/by\\s+");
                if (split.length == 2) {
                    taskList.add(new Deadline(split[0], split[1]));
                } else {
                    System.out.println("Wrong format.");
                }
                break;
            case "event":
                String[] split2 = input.substring(6).split("\\s+/at\\s+");
                if (split2.length == 2) {
                    taskList.add(new Event(split2[0], split2[1]));
                } else {
                    System.out.println("Wrong format.");
                }
                break;
            case "done":
                try {
                    taskList.markAsDone(Integer.parseInt(inputArray[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid task");
                }
                break;
            case "list":
                taskList.list();
                break;
            default:
                System.out.println("I'm not sure what you're talking about.");
            }
            input = scanner.nextLine();
        }
    }
}
