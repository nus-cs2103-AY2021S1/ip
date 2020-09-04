package duke.commands;

import duke.*;
import duke.util.OutputUi;
import duke.util.Storage;
import duke.util.TaskList;
import duke.tasks.Task;

import java.io.IOException;

/**
 * Command when a new Task is to be added to TaskList.
 */
public class AddCommand extends Command {
    static String[] words;

    /**
     * Constructor.
     * @param words Array that contains the different segments of a Task input.
     */
    public AddCommand(String[] words) {
        this.words = words;
    }

    /**
     * Adds a Todo, Event or Deadline object to Tasklist.
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException a DukeException.
     * @throws IOException an IOException.
     */
    public String execute(TaskList tasks, OutputUi ui, Storage storage) throws DukeException {
        String command = words[0];
        Task t;

        switch (command) {
        case "todo":
            t = tasks.addToDo(words[1]);
            break;
        case "deadline":
            t = tasks.addDeadline(words[1], words[2]);
            break;
        case "event":
            t = tasks.addEvent(words[1], words[2]);
            break;
        default:
            throw new DukeException("Word not recognised!");
        }
        assert t != null;

        ui.reset();
        ui.addSentence("pingu has added: " + t);
        ui.addSentence("\tnumber of tasks: " + tasks.getTasklist().size());

        super.execute(tasks, ui, storage);
        return ui.getResponse();
    }

}
