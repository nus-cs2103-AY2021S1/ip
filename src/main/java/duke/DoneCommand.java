package duke;

/**
 * Encapsulates a Done Command.
 */
public class DoneCommand extends Command {

    /**
     * Instantiates a Done command.
     * @param parsedCommand command that has been parsed
     */
    public DoneCommand(String[] parsedCommand) {
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
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException{
        try {
            // if length is not 2, nothing was passed in after 'done'
            if (parsedCommand.length != 2) {
                throw new DukeException("Which task are you done with? Please key in the task number!\n");
            }
            // check if taskNumber is a number
            int taskNumber = Integer.parseInt(parsedCommand[1]);
            // check if taskNumber is valid
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DukeException("Sorry, no such task to mark as done!\n");
            }

            Task currentTask = tasks.get(taskNumber - 1);
            Task newTask = currentTask.markAsDone();
            tasks.set(taskNumber - 1, newTask);
            System.out.println("Sugoi! This task is done!");
            System.out.println(newTask + "\n");

        } catch (NumberFormatException e) {
            throw new DukeException(parsedCommand[1] + " is not an integer!\n");
        }
    }
}
