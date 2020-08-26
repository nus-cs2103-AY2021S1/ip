import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Butler {
    public static void main(String[] args) {

        // Greetings
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today, Master?\n";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        // Reply Loop
        while(!input.equals("bye")) {

            // List all tasks
            if (input.equals("list")) {
                String listString = "\nHere are your list of tasks, Master.\n"
                        + "You have " + taskList.size()
                        + (taskList.size() > 1 ? " tasks" : " task")
                        + " in total.\n";
                int index = 0;
                for (Task task : taskList) {
                    index++;
                    listString += "\n" + index + ": " + task;
                }

                System.out.println(listString + "\n");

            // Complete tasks
            } else if (input.split(" ")[0].equals("done")) {
                String[] indexList = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
                String reply = "\n";

                for (String index : indexList) {
                    try {
                        int i = Integer.parseInt(index);
                        taskList.get(i - 1).markComplete();
                        reply += "Task " + i + " has been marked as complete.\n";
                    } catch (Exception e) {
                        reply += "Please give a valid index. \""
                                + index + "\" is not a valid index.\n";
                    }
                }

                if (indexList.length == 0) {
                    reply += "No index was given. Please provide a valid index.\n";
                }
                System.out.println(reply);

            // Delete from list
            } else if (input.split(" ")[0].equals("delete")) {
                String reply;
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    taskList.remove(index-1);
                    reply = "\nTask " + index + " has been deleted.\n";
                } catch (Exception e) {
                    reply = "\nPlease provide a valid index.\n";
                }

                System.out.println(reply);

            // Add to list
            } else {

                String[] inputList = input.split(" ", 2);
                String reply;
                Task addedTask;
                String summary;
                try {
                    switch (inputList[0]) {
                        case "todo":
                            try {
                                addedTask = new ToDoTask(inputList[1]);
                                taskList.add(addedTask);
                                reply = "\nI have added your ToDo task as follows:\n"
                                        + "Added: " + addedTask + "\n";
                                System.out.println(reply);
                            } catch (Exception e) {
                                System.out.println("\nExcuse me, " +
                                        "but I will need a description for the ToDo task given.\n");
                            }
                            break;

                        case "deadline":
                            try {
                                summary = inputList[1].split(" /by ", 2)[0];
                                String deadline = inputList[1].split(" /by ", 2)[1];
                                LocalDate formattedDeadline = LocalDate.parse(deadline);
                                addedTask = new DeadlineTask(summary, formattedDeadline);
                                taskList.add(addedTask);
                                reply = "\nI have added your Deadline task as follows:\n"
                                        + "Added: " + addedTask + "\n";
                                System.out.println(reply);
                            } catch (DateTimeParseException e) {
                                    System.out.println("\nPlease input a valid Date format.\n");
                            } catch (Exception e) {
                                System.out.println("\nExcuse me, " +
                                        "please provide a summary and deadline using the tag /by.\n");
                            }
                            break;
                        case "event":
                            try {
                                summary = inputList[1].split(" /at ", 2)[0];
                                String time = inputList[1].split(" /at ", 2)[1];
                                LocalDate startDate = LocalDate.parse(time.split(" ")[0]);
                                LocalDate endDate = LocalDate.parse(time.split(" ")[1]);
                                addedTask = new EventTask(summary, startDate, endDate);
                                taskList.add(addedTask);
                                reply = "\nI have added your Event task as follows:\n"
                                        + "Added: " + addedTask + "\n";
                                System.out.println(reply);
                            } catch (DateTimeParseException e) {
                                    System.out.println("\nPlease input a valid Date format.\n");
                            } catch (Exception e) {
                                System.out.println("\nExcuse me, " +
                                        "please provide a summary and time of event using the tag /at.\n");
                            }
                            break;

                        default:
                            System.out.println("\nI am sorry, but I do not understand this command.\n");
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid command given.\n");
                }
            }

            input = sc.nextLine();
        }

        sc.close();

        // Farewells
        String farewells = "\nIt is my honor to have served you.\n"
                + "Please come back again.";
        System.out.println(farewells);
    }
}
