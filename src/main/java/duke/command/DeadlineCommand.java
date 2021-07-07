package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to add a new Deadline object to the TaskList
 */
public class DeadlineCommand extends Command {

    private String description;
    private String by;

    /**
     * Constructs a command to add a deadline task with a String description and a String time.
     *
     * @param description String description of the Deadline Task Object.
     * @param by          String due time of the Deadline Task Object.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Creates a new Deadline Task and adds it into the TaskList. The Storage
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
        Task deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);

        //print output
        String response = ui.printTaskAdded(tasks, deadline);

        //update storage
        taskStorage.saveListToHardDisk(tasks);

        return response;
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
