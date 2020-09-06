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
        BYE
    }

    public static Storage storage;
    public static Ui ui;
    public static TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String run(String input) {
        assert !input.isEmpty();

        Command c = Parser.parse(input);

        switch (c) {
            case ADD:
                try {
                    Task curr = tasks.addItem(input);
                    return ui.addedItem(curr, tasks.getListSize());
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case DONE:
                try {
                    Task curr = tasks.doneItem(input);
                    return ui.doneItem(curr);
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case DELETE:
                try {
                    Task curr = tasks.deleteItem(input);
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
                    return ui.returnList(tasks.find(input));
                } catch (DukeException ex1) {
                    return ui.showError(ex1.toString());
                }
            case ERROR:
                return ui.defaultError();
            case BYE:
                return ui.bye();
        }
        return input;
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

