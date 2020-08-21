import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> toDoList;
    private Ui ui;

    private Duke() {
        ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();


        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|";

        System.out.println(logo);

        duke.displayThis("Hello! I'm Duke, your Windows Task Manager" +
                "\n    Valid command:" +
                "\n    - list" +
                "\n    - done <num>" +
                "\n    - todo <task>" +
                "\n    - deadline <task> /by <yyyy-mm-dd>" +
                "\n    - event <task> /at <yyyy-mm-dd>");

        try {
            toDoList = DukeFileHandler.readFile();
            if (toDoList.size() > 0) {
                duke.displayList();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);


        while (true) {
            String input = scanner.nextLine().trim();

            try {
                if (!duke.startCommand(input)) {
                    break;
                }

            } catch (DukeException ex) {
                duke.displayThis(ex.getMessage());
            }

        }
    }


    private boolean startCommand(String input) throws DukeException {

        if (input.equals("")) {
            throw new DukeException("Please type a command");
        }

        String command = input.contains(" ") ?
                input.split(" ")[0].toLowerCase() :
                input.toLowerCase();


        switch (command) {
        case "bye":
            try {
                DukeFileHandler.writeToFile(toDoList);
            } catch (IOException e) {
                e.printStackTrace();
            }

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

                displayThis("OKay, I've remove this task: \n        " + temp.toString() +
                        "\n    Now you have " + toDoList.size() + " tasks in the list");

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


    private void addTask(Task task) {
        toDoList.add(task);
        displayThis("Got it. I've added this task: \n         " + task +
                "\n    Now you have " + toDoList.size() + " tasks in the list");
    }


    private void displayThis(String s) {
        System.out.println("\n    ---------------------------------");
        System.out.println("    " + s);
        System.out.println("    ---------------------------------\n");
    }


    private void displayList() {
        System.out.println("\n    ---------------------------------\n" +
                "    Here are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + toDoList.get(i));
        }
        System.out.println("    ---------------------------------");
    }


}