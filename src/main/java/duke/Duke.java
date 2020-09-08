package duke;

import duke.task.TaskList;

import duke.command.Command;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String output = command.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

}

