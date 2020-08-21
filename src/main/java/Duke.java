import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //    private List<Task> toDoList;
    private final Ui ui;
    private final DukeFileHandler fileHandler;
    private TaskList tasks;

    private Duke() {
        ui = new Ui();
        ui.welcome();

        fileHandler = new DukeFileHandler("data/dukeData.txt");


        // todo TaskList

        // todo add parse, a class to make sense of user commands


        try {
            tasks = new TaskList(fileHandler.readFile());
//            toDoList = fileHandler.readFile();
            if (!tasks.isNull()) {
                ui.displayList(tasks.getList());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList(new ArrayList<>());
        }

        startListeningToCommand();
    }

    private void startListeningToCommand() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                if (!command(input)) {
                    break;
                }

            } catch (DukeException ex) {
                ui.displayThis(ex.getMessage());
            }

        }
    }


    private boolean command(String input) throws DukeException {

        if (input.equals("")) {
            throw new DukeException("Please type a command");
        }

        String command = input.contains(" ") ?
                input.split(" ")[0].toLowerCase() :
                input.toLowerCase();


        switch (command) {
        case "bye":
            try {
                fileHandler.writeToFile(tasks.getList());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ui.displayThis("Bye. Hope to see you again soon!");
            return false;

        case "clear":
            tasks.clear();
            ui.displayThis("List is cleared");
            break;

        case "list":
            if (tasks.isNull()) {
                throw new DukeException("There's nothing in the list :-(");
            } else {
                ui.displayList(tasks.getList());
            }
            break;

        case "done":
            try {
                int entryDone = Integer.parseInt(input.substring(5)) - 1;
//                Task temp = toDoList.get(entryDone);
//                temp.markAsDone();
                ui.displayThis("Nice! I've marked this task as done: \n        " + tasks.done(entryDone));

            } catch (Exception ex) {
                throw new DukeException("This task does not exist");
            }

            break;

        case "delete":
            try {
                int entryDelete = Integer.parseInt(input.substring(7)) - 1;
//                Task temp = toDoList.get(entryDelete);
//                toDoList.remove(entryDelete);

                ui.displayThis("OKay, I've remove this task: \n        " + tasks.delete(entryDelete) +
                        "\n    Now you have " + tasks.size() + " tasks in the list");

            } catch (Exception ex) {
                throw new DukeException("This task does not exist");
            }

            break;

        case "deadline": {
            try {
                int timing = input.indexOf(" /by");
                String description = input.substring(9, timing);
                String by = input.substring(timing + 5);
                tasks.addTask(new Deadline(description, by));

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
                tasks.addTask(new Events(description, at));

            } catch (Exception ex) {
                throw new DukeException("Event format isn't correct");
            }

            break;

        }
        case "todo":
            tasks.addTask(new Task(input.substring(5)));
            break;

        default:
            throw new DukeException("I don't know what that means :-(");

        }
        return true;
    }


//    private void addTask(Task task) {
//        toDoList.add(task);
//        ui.displayThis("Got it. I've added this task: \n         " + task +
//                "\n    Now you have " + toDoList.size() + " tasks in the list");
//    }


    public static void main(String[] args) {
        new Duke();
    }

}