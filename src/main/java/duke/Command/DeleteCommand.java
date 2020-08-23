package duke.Command;

import duke.Exception.DeletionException;
import duke.Storage;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand object with the input specified
     * @param input User's input that is processed by the DeleteCommand Object
     * @param index index of the task to remove
     */
    public DeleteCommand(String input, int index) {
        super(input);
        this.index = index;
    }

    /**
     * Invokes the DeleteCommand object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     * @throws DeletionException if index does not exist in tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeletionException {
        if (index >= tasks.size() || index < 0) {
            throw new DeletionException("Item does not exist in list!");
        }

        Task task = tasks.get(index);
        tasks.remove(index);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Returns false as DeleteCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
