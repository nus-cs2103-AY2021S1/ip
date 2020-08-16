import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
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


        while (true) {
            String input = scanner.nextLine().trim();

            try {
                if (!startCommand(input)) {
                    break;
                }

            } catch (DukeException ex) {
                displayError(ex.getMessage());
            }

        }
    }


    private static final ArrayList<Task> toDoList = new ArrayList<>();

    private static boolean startCommand(String input) throws DukeException {

        if (input.equals("")) {
            throw new DukeException("Please type a command");
        }

        String command = input.contains(" ") ?
                input.split(" ")[0].toLowerCase() :
                input.toLowerCase();


        switch (command) {
            case "bye":
                displayThis("Bye. Hope to see you again soon!");
                return false;

            case "list":
                if (toDoList.size() == 0) {
                    throw new DukeException("There's nothing in the list :-(");
                }
                displayList();
                break;

            case "done":
                try {
                    int entryDone = Integer.parseInt(input.substring(5)) - 1;
                    Task temp = toDoList.get(entryDone);
                    temp.markAsDone();
                    displayThis("Nice! I've marked this task as done: \n        " + temp.toString());

                } catch (Exception ex) {
                    throw new DukeException("This task does not exist");
                }

                break;

            case "delete":
                try {
                    int entryDelete = Integer.parseInt(input.substring(7)) - 1;
                    Task temp = toDoList.get(entryDelete);
                    toDoList.remove(entryDelete);

                    displayThis("OKay, I've remove this task: \n        " + temp.toString());

                } catch (Exception ex) {
                    throw new DukeException("This task does not exist");
                }

                break;

            case "deadline": {
                try {
                    int timing = input.indexOf(" /by");
                    String description = input.substring(9, timing);
                    String by = input.substring(timing + 5);
                    addTask(new Deadline(description, by));

                } catch (Exception ex) {
                    throw new DukeException("Deadline format isn't correct");
                }

                break;
            }

            case "event": {
                try {
                    int timing = input.indexOf(" /at");
                    String description = input.substring(6, timing);
                    String at = input.substring(timing + 5);
                    addTask(new Events(description, at));

                } catch (Exception ex) {
                    throw new DukeException("Event format isn't correct");
                }

                break;

            }
            case "todo":
                addTask(new Task(input.substring(5)));
                break;

            default:
                throw new DukeException("I don't know what that means :-(");

        }
        return true;
    }


    private static void addTask(Task task) {
        toDoList.add(task);
        displayThis("Got it. I've added this task: \n         " + task);
    }


    private static void displayThis(String s) {
        System.out.println("\n    ---------------------------------");
        System.out.println("    " + s);
        System.out.println("    Now you have " + toDoList.size() + " tasks in the list");
        System.out.println("    ---------------------------------\n");
    }


    private static void displayError(String s){
        System.out.println("\n    ---------------------------------");
        System.out.println("    " + s);
        System.out.println("    ---------------------------------\n");

    }


    private static void displayList() {
        System.out.println("\n    ---------------------------------\n" +
                "    Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + toDoList.get(i));
        }
        System.out.println("    ---------------------------------");
    }


}