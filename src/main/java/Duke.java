package main.java;

import java.time.DateTimeException;
import java.time.LocalDate;
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
        String input = scanner.nextLine().trim();
        TaskList taskList = new TaskList();
        while (true) {
            try {
                String[] inputArray = input.split("\\s+");
                switch (inputArray[0]) {
                case "bye":
                    System.out.println("Have a nice day.");
                    return;
                case "":
                    break;
                case "todo":
                    taskList.add(ToDo.of(input));
                    break;
                case "deadline":
                    Deadline deadline = Deadline.of(input);
                    if (deadline != null) {
                        taskList.add(deadline);
                    }
                    break;
                case "event":
                    Event event = Event.of(input);
                    if (event != null) {
                        taskList.add(event);
                    }
                    break;
                case "done":
                    if (inputArray.length != 2) {
                        throw new DukeException("Wrong format.");
                    }
                    try {
                        taskList.markAsDone(Integer.parseInt(inputArray[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("Wrong format.");
                    }
                    break;
                case "delete":
                    if (inputArray.length != 2) {
                        throw new DukeException("Wrong format.");
                    }
                    try {
                        taskList.delete(Integer.parseInt(inputArray[1]));
                    } catch (NumberFormatException e) {
                        throw new DukeException("Wrong format.");
                    }
                    break;
                case "list":
                    if (inputArray.length > 1) {
                        try {
                            LocalDate date = LocalDate.parse(inputArray[1]);
                            taskList.list(date);
                        } catch (DateTimeException e) {
                            System.out.println("Please provide date in yyyy-mm-dd format.");
                        }
                    } else {
                        taskList.list();
                    }
                    break;
                default:
                    throw new DukeException("I'm not sure what you're talking about.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine().trim();
        }
    }
}
