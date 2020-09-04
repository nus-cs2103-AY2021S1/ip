package duke;

import java.util.ArrayList;

import duke.commands.*;
import duke.util.*;

/**
 * Engages all the other classes to execute Duke.
 */
public class Duke {
    private TaskList tasks;
    private OutputUi ui;
    private Storage storage;


    /**
     * Constructor.
     */
    public Duke() {
        ui = new OutputUi();
        storage = new Storage();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null;
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

