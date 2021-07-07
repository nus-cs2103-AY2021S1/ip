package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to add a new Event object to the TaskList
 */
public class EventCommand extends Command {

    private String description;
    private String at;

    /**
     * Constructs a command with a String description and a String time.
     *
     * @param description String description of the Event Task Object.
     * @param at          String time of the Event Task Object.
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Creates a new Event Task and adds it into the TaskList. The Storage
     * is updated with the latest Task and a relevant String message to notify
     * the user on this addition will be returned.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param notes   NoteList object containing the list of notes.
     * @param ui      Ui object to output messages to the user.
     * @param taskStorage Storage object for storing tasks.
     * @param noteStorage Storage object for storing notes.
     * @return String response to user.
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage) {
        Task event = new Event(this.description, this.at);
        tasks.add(event);

        //print output
        String output = ui.printTaskAdded(tasks, event);

        //update storage
        taskStorage.saveListToHardDisk(tasks);

        return output;
    }

    /**
     * Returns false to indicate that the Command does not exit the program.
     *
     * @return Exit program indicator
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
