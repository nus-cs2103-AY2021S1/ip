package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object with the input specified
     * @param input User's input that is processed by the ListCommand Object
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Invokes the ListCommand object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks.getList()) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }

    /**
     * Returns false as ListCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}