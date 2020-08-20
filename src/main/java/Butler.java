import java.util.Scanner;
import java.util.ArrayList;

public class Butler {
    public static void main(String[] args) {

        // Greetings
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today, Master?\n";
        System.out.println(greetings);

        // Reply Loop
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<String> taskList = new ArrayList<>();

        while(!input.equals("bye")) {
            //List all tasks
            if (input.equals("list")) {
                String listString = "\nHere are your list of tasks, Master.\n"
                        + "You have " + taskList.size() + " tasks in total.\n";

                int index = 0;
                for (String task: taskList) {
                    index++;
                    listString += "\n" + index + ": " + task;
                }

                System.out.println(listString + "\n");
            } else {
                //Add to list
                taskList.add(input);
                String reply = "\nI have added your command as follows:\n"
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
