import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                "     ____        _        \n"
                        + "    |  _ \\ _   _| | _____ \n"
                        + "    | | | | | | | |/ / _ \\\n"
                        + "    | |_| | |_| |   <  __/\n"
                        + "    |____/ \\__,_|_|\\_\\___|";


        System.out.println(logo);

        displayThis("Hello! I'm Duke\n    What can I remember for you?");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayList<Task> toDoList = new ArrayList<Task>();

        while (!input.equals("bye")) {

            if (input.equals("list")) {

                if (toDoList.size() == 0) { // checks if the user types list to a empty list
                    displayThis("List is empty");

                } else {

                    int temp = 0;
                    System.out.println("\n    ---------------------------------\n" +
                            "    Here are the tasks in your list:");
                    for (int i = 0; i < toDoList.size() && toDoList.get(i) != null; i++) {

                        temp = i + 1;
                        System.out.println("    " + temp + ". " + toDoList.get(i));

                    }
                    System.out.println("    ---------------------------------");
                }

            } else if (input.toLowerCase().contains("done")) {
                int entryDone =  Integer.parseInt(input.substring(input.length() - 1)) - 1;

                Task temp = toDoList.get(entryDone);

                temp.markAsDone();

                displayThis("Nice! I've marked this task as done: \n        " +  temp.toString());

            } else if (!input.equals("")) {
                toDoList.add(new Task(input));

                displayThis("added: " + input);
            }


            input = scanner.nextLine().trim();
        }

        displayThis("Bye. Hope to see you again soon!");

    }

    private static void displayThis(String s) {
        System.out.println("\n    ---------------------------------");

        System.out.println("    " + s);

        System.out.println("    ---------------------------------\n");

    }

}