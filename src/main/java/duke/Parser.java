package duke;

import duke.commands.Commands;

/**
 * Class to parse user input and process it.
 */
public class Parser {

    TaskList taskList;
    Storage storage;
    UI ui;

    /**
     * Creates a Parser with the given classes.
     * @param taskList Class to store and manage tasks
     * @param storage Class to write task data to disk
     * @param ui Class to define interface with user
     */
    public Parser(TaskList taskList, Storage storage, UI ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses and processes the input string.
     * @param input String input from the user
     * @return false if the user exits
     * @throws DukeException Duke-related exception due to erroneous inputs
     */
    public boolean processInput(String input) throws DukeException {
        if (Commands.BYE.check(input)) {
            exit();
            return false;
        } else if (Commands.LIST.check(input)) {
            ui.writeOutput(taskList.listTasks());
        } else {
            String[] inputSplit = input.split(" ", 2);
            if (Commands.TODO.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
                ui.writeAdd(taskList.addTask(inputSplit[1]), taskList.getSize());
            } else if (Commands.DEADLINE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty");
                }
                ui.writeAdd(taskList.addTask(inputSplit[1], false), taskList.getSize());
            } else if (Commands.EVENT.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("The description of an event cannot be empty");
                }
                ui.writeAdd(taskList.addTask(inputSplit[1], true), taskList.getSize());
            } else if (Commands.DONE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("duke.tasks.Task number cannot be empty");
                }
                try {
                    ui.writeDone(taskList.markDone(Integer.parseInt(inputSplit[1])));
                } catch (NumberFormatException ex) {
                    throw new DukeException("duke.tasks.Task number must be a valid integer");
                }
            } else if (Commands.DELETE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("duke.tasks.Task number cannot be empty");
                }
                try {
                    ui.writeDelete(taskList.deleteTask(Integer.parseInt(inputSplit[1])), taskList.getSize());
                } catch (NumberFormatException ex) {
                    throw new DukeException("duke.tasks.Task number must be a valid integer");
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :(");
            }
            storage.storeList(taskList.getList());
        }
        return true;
    }

    private void exit() {
        ui.exit();
    }
}
