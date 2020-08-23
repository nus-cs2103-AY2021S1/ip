package duke.Command;

import duke.Exception.DoneException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private final int listNumber;

    /**
     * Constructs a DoneCommand object with the input and listNumber specified
     * @param input User's input that is processed by the DoneCommand Object
     * @param listNumber listNumber of the task to mark as complete
     */
    public DoneCommand(String input, int listNumber) {
        super(input);
        this.listNumber = listNumber;
    }

    /**
     * Invokes the DoneCommand object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     * @throws DoneException if task does not exist in tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoneException {
        if (listNumber > tasks.size() || listNumber <= 0) {
            throw new DoneException("Item number " + listNumber + " does not exist in list!");
        }
        tasks.get(listNumber - 1).completeTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(listNumber - 1).toString());
    }

    /**
     * Returns false as DoneCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
