package main.java;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Parser {

    public static void parse(String input, TaskList taskList, Ui ui) throws DukeException {

        if (input.equals("list")) {

            taskList.printList();

        } else if (input.indexOf("done ") == 0) {

            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]) - 1;
            Task task = taskList.completeTask(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);

        } else if (input.indexOf("delete ") == 0) {

            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]) - 1;
            Task task = taskList.removeTask(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");

        } else if (input.indexOf("search ") == 0) {

            if (input.contains("/on ")) {

                int index = input.indexOf("/on ");

                String dateString = input.substring(index + 4);

                try {
                    LocalDate date = LocalDate.parse(dateString);

                    taskList.printList((task) -> {

                        if (task instanceof Event && ((Event) task).getDate().equals(date)) {
                            return true;
                        }

                        if (task instanceof Deadline && ((Deadline) task).getDate().equals(date)) {
                            return true;
                        }

                        return false;
                    });


                } catch (DateTimeException e) {
                    throw new DukeException("Enter date in the following format: YYYY-MM-DD");
                }

            } else {
                throw new DukeException("Enter a valid /on parameter");
            }

        } else {

            if (input.indexOf("todo ") == 0 && input.length() > 5) {

                taskList.addTask(new ToDo(input.substring(5)));

            } else if (input.indexOf("deadline ") == 0) {

                int deadlineIndex = input.indexOf("/by ");

                if (deadlineIndex != -1 && input.length() > deadlineIndex + 4) {

                    String datetime = input.substring(deadlineIndex + 4);

                    try {
                        if (datetime.contains(" ")) {
                            String[] datetimeArr = datetime.split(" ");
                            taskList.addTask(new Deadline(input.substring(9, deadlineIndex - 1), datetimeArr[0], datetimeArr[1]));
                        } else {
                            taskList.addTask(new Deadline(input.substring(9, deadlineIndex - 1), datetime, ""));
                        }
                    } catch (DateTimeException e) {
                        throw new DukeException("Enter date in the following format: YYYY-MM-DD HH:mm(optional) " +
                                "(e.g. 2020-06-18 or 2020-07-20 18:00)");
                    }
                }

            } else if (input.indexOf("event ") == 0) {

                int timeIndex = input.indexOf("/at ");

                if (timeIndex != -1 && input.length() > timeIndex + 4) {

                    String datetime = input.substring(timeIndex + 4);

                    try {
                        if (datetime.contains(" ")) {
                            String[] datetimeArr = datetime.split(" ");
                            taskList.addTask(new Event(input.substring(6, timeIndex - 1), datetimeArr[0], datetimeArr[1]));
                        } else {
                            taskList.addTask(new Event(input.substring(6, timeIndex - 1), datetime, ""));
                        }
                    } catch (DateTimeException e) {
                        throw new DukeException("Enter date in the following format: YYYY-MM-DD HH:mm(optional) " +
                                "(e.g. 2020-06-18 or 2020-07-20 18:00)");
                    }
                }

            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println("Got it. I've added this task:");
            taskList.printTask(taskList.getSize() - 1);
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        }

        ui.printLine();
    }
}
