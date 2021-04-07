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

    /**
     * Creates a Duke.
     *
     * @param filePath which saved data is retrieved. If saved data is not found, a directory will be created.
     */
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
                    storage.overwriteData(tasks.getList());
                    return ui.addedItem(curr, tasks.getListSize());
                } catch (DukeException | IOException ex1) {
                    return ui.showError(ex1.toString());
                }
            case DONE:
                try {
                    Task curr = tasks.doneItem(nInput);
                    storage.overwriteData(tasks.getList());
                    return ui.doneItem(curr);
                } catch (DukeException | IOException ex1) {
                    return ui.showError(ex1.toString());
                }
            case DELETE:
                try {
                    Task curr = tasks.deleteItem(nInput);
                    storage.overwriteData(tasks.getList());
                    return ui.deleteItem(curr);
                } catch (DukeException | IOException ex1) {
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
                try {
                    storage.overwriteData(tasks.getList());
                    return ui.bye();
                } catch (IOException ex1) {
                    return ui.showError(ex1.getLocalizedMessage());
                }
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

