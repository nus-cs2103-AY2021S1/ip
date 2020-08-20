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
                        + "You have " + taskList.size() + " tasks in total.\n";

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
                        taskList.get(i-1).markComplete();
                        reply += "Task " + i + " has been marked as complete.\n";
                    } catch (Exception e) {
                        reply += "Please give a valid index. \""
                                + index + "\" is not a valid index.\n";
                    }
                }

                if (indexList.length == 0) {
                    reply += "Please a give a valid index.\n";
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
                            addedTask = new ToDoTask(inputList[1]);
                            taskList.add(addedTask);
                            reply = "\nI have added your ToDo task as follows:\n"
                                    + "Added: " + addedTask + "\n";
                            System.out.println(reply);
                            break;
                        case "deadline":
                            summary = inputList[1].split(" /by ", 2)[0];
                            String deadline = inputList[1].split(" /by ", 2)[1];
                            addedTask = new DeadlineTask(summary, deadline);
                            taskList.add(addedTask);
                            reply = "\nI have added your Deadline task as follows:\n"
                                    + "Added: " + addedTask + "\n";
                            System.out.println(reply);
                            break;
                        case "event":
                            summary = inputList[1].split(" /at ", 2)[0];
                            String time = inputList[1].split(" /at ", 2)[1];
                            addedTask = new EventTask(summary, time);
                            taskList.add(addedTask);
                            reply = "\nI have added your Event task as follows:\n"
                                    + "Added: " + addedTask + "\n";
                            System.out.println(reply);
                            break;
                        default:
                            System.out.println("\nInvalid command given.\n");
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
