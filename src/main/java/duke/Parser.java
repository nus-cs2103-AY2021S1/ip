package duke;

import duke.commands.Commands;

public class Parser {

    TaskList taskList;
    Storage storage;
    UI ui;

    public Parser(TaskList taskList, Storage storage, UI ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

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
                    throw new DukeException("Task number cannot be empty");
                }
                try {
                    ui.writeDone(taskList.markDone(Integer.parseInt(inputSplit[1])));
                } catch (NumberFormatException ex) {
                    throw new DukeException("Task number must be a valid integer");
                }
            } else if (Commands.DELETE.check(inputSplit[0])) {
                if (inputSplit.length < 2) {
                    throw new DukeException("Task number cannot be empty");
                }
                try {
                    ui.writeDelete(taskList.deleteTask(Integer.parseInt(inputSplit[1])), taskList.getSize());
                } catch (NumberFormatException ex) {
                    throw new DukeException("Task number must be a valid integer");
                }
            } else if (Commands.FIND.check((inputSplit[0]))) {
                if (inputSplit.length != 2) {
                    throw new DukeException("Search key cannot be empty");
                }
                ui.writeSearch(taskList.findTasks(inputSplit[1]));
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
