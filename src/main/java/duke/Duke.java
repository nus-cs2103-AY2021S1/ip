package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        this.tasks = new TaskList();

        try {
            storage.loadData(tasks);
            ui.fileLoaded();
        } catch (DukeException e) {
            ui.showErrorMsg(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.sayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String nextCommand = this.ui.getNextCommand();
                Command c = Parser.parse(nextCommand);
                if (c != null) {
                    c.execute(this.ui, this.storage, this.tasks);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                ui.showErrorMsg(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }


    //    public static void main(String[] args) throws DukeException {
//
//        while (!shouldStop) {
//            String[] line = scanner.nextLine().split(" ", 2);
//            String operation = line[0];
//            switch (operation) {
//            case "bye":
//                Storage.writeToFile(taskList);
//                shouldStop = true;
//                System.out.println("Bye bye. See you again soon!");
//                break;
//            case "list":
//                try {
//                    Helper.showList(taskList);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//                break;
//            case "done":
//                try {
//                    Helper.markAsDone(line, taskList);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//                break;
//            case "todo":
//            case "deadline":
//            case "event":
//                if (line.length == 1) {
//                    System.out.println("☹ OOPS! Description for command '"
//                            + operation + "' not found, try again!");
//                    break;
//                } else if (operation.equals("todo")) {
//                    try {
//                        Helper.addToDo(line, taskList);
//                    } catch (DukeException e) {
//                        System.out.println(e.getMessage());
//                    }
//                } else if (operation.equals("deadline")) {
//                    try {
//                        Helper.addDeadline(line, taskList);
//                    } catch (DukeException e) {
//                        System.out.println(e.getMessage());
//                    }
//                } else {
//                    try {
//                        Helper.addEvent(line, taskList);
//                    } catch (DukeException e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//                break;
//            case "delete":
//                try {
//                    Helper.deleteTask(line, taskList);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//                break;
//            default:
//                // Handles all other inputs
//                System.out.println("☹ Sorry, I don't recognise that command! "
//                        + "Try one of the following instead: todo, event, deadline, done or delete");
//            }
//            Storage.writeToFile(taskList);
//        }
//    }
}