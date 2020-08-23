package duke.command;

import duke.task.Task;
import duke.component.*;

public class AddCommand extends Command {
    /**
     * Creates a command for adding tasks.
     * @param input the input command classified as AddCommand
     */
    public AddCommand(String input) {
        super(input);
    }

    /**
     * Executes the command, prints the result on ui and writes to source data file.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string that is to be printed on ui if the adding is successful
     * @throws InvalidCommandException if the input command doesn't make sense and states why
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int count = list.size();
        Task task = Parser.generate(input);
        storage.addToList(task);
        list.add(count, task);
        String temp = count < 1 ? " task" : " tasks";
        String res = "Got it. I've added this task:\n\t    " + task +
                "\n\t  Now you have " + (count + 1) + temp + " in the list.";
        ui.output(res);
        return res;
    }

    /**
     * Checks whether a command equals this one.
     * @param obj the Object to compare
     * @return true if obj is a AddCommand and it has the same input as this one
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof AddCommand) {
            return input.equals(((AddCommand) obj).input);
        } else {
            return false;
        }
    }
}
