package duke;

/**
 * Encapsulates a List Command
 */
public class ListCommand extends Command {
    public ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes command and write to storage.
     * @param tasks the tasklist containing tasks so far
     * @param ui ui to interact with user
     * @param storage storage to read and write to storage file
     * @throws DukeException if parsedCommand does not meet the requirements
     */
    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment! Good job!\n");
            return;
        }

        System.out.println("Here are your tasks!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". "
                    + tasks.get(i));
        }
        System.out.println();
    }
}
