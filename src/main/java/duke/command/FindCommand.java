package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find items in the task list.
 */
public class FindCommand extends Command {

    private String searchTerm;

    /**
     * Constructs a command with search term to be used for filtering out matching
     * task descriptions in a specified TaskList.
     *
     * @param searchTerm Search description for tasks.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Uses the searchTerm attribute to filter out tasks with descriptions containing the searchTerm. The tasks
     * are added into a new TaskList and a string response will be returned.
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

        //Initialize new TaskList for matching tasks
        TaskList searchTasks = new TaskList();

        //loop through to find matching tasks to add to new TaskList
        tasks.getList().stream()
                .filter(task -> task.getDescription().contains(this.searchTerm))
                .forEach(searchTasks::add);

        //print TaskList of matching tasks
        String output = ui.printMatchingTasks();
        output += "\n";
        output += ui.printList(searchTasks);

        return output;
    }

    /**
     * Returns false to indicate that the Command does not exit the program.
     *
     * @return false indicator
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
