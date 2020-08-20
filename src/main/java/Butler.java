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
                taskList.add(new Task(input));
                String reply = "\nI have added your task as follows:\n"
                        + "Added: " + input + "\n";
                System.out.println(reply);
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
