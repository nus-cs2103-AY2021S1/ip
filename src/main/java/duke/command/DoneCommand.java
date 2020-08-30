package duke.command;

import java.util.stream.Stream;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DoneException;
import duke.exception.DukeException;

public class DoneCommand extends Command {

    private String[] tasks;

    public DoneCommand(String... tasks) {
        this.tasks = tasks;
    }

    /**
     * Processes all the done command to determine the correct output.
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return processDone(this.tasks, taskList, ui, storage);
        } catch (DoneException done) {
            return done.getMessage();
        }
    }

    /**
     * Processes all the done command to determine the correct output.
     * @param taskNumbers Parsed string containing task numbers.
     * @param taskList    List containing all the task(s).
     * @param ui          UI of the bot
     * @param storage     Storage managing the file in hard disk.
     * @throws DoneException If user's input is incomplete or in the wrong format.
     */

    public String processDone(
        String[] taskNumbers, TaskList taskList, Ui ui, Storage storage) throws DoneException {
        try {
            int num = 1;
            Integer[] parsedNumbers = Stream.of(taskNumbers).map(Integer::valueOf).toArray(Integer[]::new);
            StringBuilder builder = new StringBuilder();
            builder.append("YAYY! I've marked these task(s) as done\n");

            for (int taskNum : parsedNumbers) {
                Storage.updateData(taskList.getTasks());
                builder.append(num + ". " + taskList.markTaskAsDone(taskNum) + "\n");
                num++;
            }

            return builder.toString();

        } catch (DukeException d) {
            throw new DoneException("Please enter a valid task number");
        }
    }

    /**
     * Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
     *
     * @param other Other object to compare.
     * @return True if this object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DoneCommand) {
            DoneCommand doneCommand = (DoneCommand) other;
            return this.task.equals(doneCommand.getTask());
        }
        return false;
    }
}
