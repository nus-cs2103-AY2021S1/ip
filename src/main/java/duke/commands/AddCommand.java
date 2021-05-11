package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates a Command to add a new task.
 */
public class AddCommand extends Command {
    final Task[] myTasks;

    /**
     * Initializes an instance of AddCommand.
     * @param myTasks A variable number of tasks to be added to the Task List.
     */
    public AddCommand(Task ... myTasks) {
        this.myTasks = myTasks;
    }

    /**
     * Consolidated method to perform the following actions.
     * Adds a duke.task to toDoList in duke.task.TaskList, saves the new duke.task list in a
     * txt file and prints a success message.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     * @return String being printed.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        StringBuilder returnStr = new StringBuilder();
        for (Task t : myTasks) {
            TaskList.addToList(t);
            returnStr.append(ui.printFormat(t.toString()));
        }
        storage.save(TaskList.getList());
        returnStr.append(ui.printNumberOfTasks(TaskList.getList().size()));
        return returnStr.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) o;
        boolean isEqual = true;
        for (int i = 0; i < this.myTasks.length; i++) {
            System.out.println(this.myTasks[i].getType());
            System.out.println(command.myTasks[i].getType());
            isEqual = this.myTasks[i].equals(command.myTasks[i]);
        }
        return isEqual;
    }
}

