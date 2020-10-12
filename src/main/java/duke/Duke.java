package duke;

import duke.task.Task;

import java.io.IOException;

public class Duke {
    enum Command {
        ADD,
        DONE,
        SAVE,
        DELETE,
        LIST,
        FIND,
        ERROR,
        SORTDATE,
        SORTTYPE,
        CLEAR,
        BYE
    }

    public static Storage storage;
    public static Ui ui;
    public static TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String run(String input) {
        assert !input.isEmpty();
        String nInput = input.trim();
        Command c = Parser.parse(nInput);

        switch (c) {
            case ADD:
                try {
                    Task curr = tasks.addItem(nInput);
                    return ui.addedItem(curr, tasks.getListSize());
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case DONE:
                try {
                    Task curr = tasks.doneItem(nInput);
                    return ui.doneItem(curr);
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case DELETE:
                try {
                    Task curr = tasks.deleteItem(nInput);
                    return ui.deleteItem(curr);
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case LIST:
                return ui.returnList(tasks.getList());
            case SAVE:
                try {
                    storage.overwriteData(tasks.getList());
                    return ui.save();
                } catch (IOException ex1) {
                    return ui.showError(ex1.getLocalizedMessage());
                }
            case FIND:
                try {
                    return ui.returnList(tasks.find(nInput));
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case SORTDATE:
                return ui.returnList(tasks.sortByDate());
            case SORTTYPE:
                return ui.returnList(tasks.sortByType());
            case CLEAR:
                try {
                    storage.overwriteData(tasks.clear());
                    return ui.clear();
                } catch (IOException ex1) {
                    return ui.showError(ex1.getLocalizedMessage());
                }
            case ERROR:
                return ui.DEFAULTERROR();
            case BYE:
                return ui.bye();
        }
        return DukeException.INVALID_COMMAND_EXCEPTION.toString();
    }


    public static void main(String[] args) {
        Duke d = new Duke("");
//        "data/Duke.txt"

        System.out.println(ui.welcome());

        String nextLine = ui.readCommand();

        while (!nextLine.equals("bye")) {
            d.run(nextLine);
        }
    }

    public String getResponse(String input) {
        return run(input);
    }

}

