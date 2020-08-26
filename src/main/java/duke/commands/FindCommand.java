package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;


public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printFindMessage();

        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            try {
                // Check if user input matches any task description in list
                if (taskList.getTask(i + 1).getDescription().contains(super.command)) {
                    String message = (index) + "." + taskList.getTask(i + 1);
                    System.out.println(message);
                    index++;
                }
            } catch (DukeException e) {
                e.getMessage();
            }
        }
    }
}



