package duke.Command;

import duke.Exception.FindException;
import duke.Storage;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    /**
     * Constructs a FindCommand object with the input specified
     * @param input User's input that is processed by the FindCommand Object
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Invokes the FindCommand object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FindException {
        String listOfTask = "";
        int index = 1;
        for (Task task : tasks.getList()) {
            if (task.toString().contains(input)) {
                listOfTask += index + ". " + task.toString() + "\n";
                index++;
            }
        }
        if (listOfTask.equals("")) {
            throw new FindException("Keyword is not found in any tasks!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(listOfTask);
        }
    }

    /**
     * Returns false as FindCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
