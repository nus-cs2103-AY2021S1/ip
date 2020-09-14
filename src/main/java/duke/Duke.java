package duke;

import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Duke helps you manage tasks through a chatbot.
 * Duke also saves your list of tasks and will load
 * where you left off.
 */
public class Duke {
    protected Storage storage;
    protected Ui ui;
    protected TaskList tasks;

    /**
     * Duke constructor
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.reply(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    //

    //    public static void main(String[] args) {

    //        String logo = " ____        _        \n"

    //                + "|  _ \\ _   _| | _____ \n"

    //                + "| | | | | | | |/ / _ \\\n"

    //                + "| |_| | |_| |   <  __/\n"

    //                + "|____/ \\__,_|_|\\_\\___|\n";

    //        System.out.println("Hello from\n" + logo);

    //}

    /**
     * Returns the appropriate response from the chat bot.
     *
     * @param input The user input.
     * @return The appropriate response message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
