import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|";


        System.out.println(logo);

        displayThis("Hello! I'm Duke\n    What can I remember for you?" +
                "\n    I only accept list, done and" +
                "\n    todo, deadline, events");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayList<Task> toDoList = new ArrayList<>();

        while (!input.equals("bye")) {

            String command = input.contains(" ") ?
                    input.split(" ")[0].toLowerCase() :
                    input.toLowerCase();

            if (command.equals("list")) {

                displayList(toDoList);

            } else if (command.equals("done")) {
                int entryDone = Integer.parseInt(input.substring(5)) - 1;

                System.out.println(entryDone);

                if (entryDone >= 0) {
                    Task temp = toDoList.get(entryDone);

                    temp.markAsDone();

                    displayThis("Nice! I've marked this task as done: \n        " + temp.toString());

                } else {
                    displayThis("Command done is in the wrong format");
                }


            } else if (!command.equals("")) {
                Task tempTask;

                switch (command) {
                    case "deadline": {
                        int timing = input.indexOf(" /by");

                        String description = input.substring(9, timing);
                        String by = input.substring(timing + 5);

                        tempTask = new Deadline(description, by);

                        break;
                    }
                    case "event": {
                        int timing = input.indexOf(" /at");

                        String description = input.substring(6, timing);
                        String by = input.substring(timing + 5);

                        tempTask = new Events(description, by);


                        break;
                    }
                    case "todo":  // to do task
                        tempTask = new Task(input.substring(5));

                        break;

                    default:
                        tempTask = new Task("Unknown type: " + input);
                        break;
                }

                toDoList.add(tempTask);

                displayThis("Got it. I've added this task: \n         " + tempTask
                        + "\n    Now you have " + toDoList.size() + " tasks in the list");
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

    private static void displayList(List<Task> list) {
        if (list.size() == 0) {
            displayThis("List is empty");

        } else {

            int temp = 0;
            System.out.println("\n    ---------------------------------\n" +
                    "    Here are the tasks in your list:");
            for (int i = 0; i < list.size() && list.get(i) != null; i++) {

                temp = i + 1;
                System.out.println("    " + temp + ". " + list.get(i));

            }

            System.out.println("    ---------------------------------");
        }
    }

}