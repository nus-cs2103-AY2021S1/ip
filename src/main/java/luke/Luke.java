package luke;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

import luke.commands.Command;
import luke.exception.*;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

public class Luke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (LukeException e) {
            this.tasks = new TaskList();
        }
        this.ui = new Ui();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(this.storage, this.tasks, this.ui);
        } catch (LukeException e) {
            return ui.showError(e);
        }
    }

    public Ui getUi() {
        return this.ui;
    }
}