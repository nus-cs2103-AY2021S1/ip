package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    protected boolean hasFoundTask = false;
    protected String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskListHandler handler, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        try {
            String keyword = input.split(" ")[1];
            for (Task t : handler.getTaskList()) {
                if (t.getDescription().contains(keyword)) {
                    foundTasks.add(t);
                    hasFoundTask = true;
                }
            }
            if (!hasFoundTask) {
                Ui.drawTopBorder();
                Ui.indent(1);
                System.out.println("I couldn't find any tasks matching " + '"' + keyword + '"' + ".");
                Ui.drawBottomBorder();
                return;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 Oops, the keyword to search for cannot be empty!");
        }
        Ui.drawTopBorder();
        Ui.indent(1);
        System.out.println("I have found the matching tasks in your list: ");
        for (Task t: foundTasks) {
            Ui.indent(2);
            System.out.println(t);
        }
        Ui.drawBottomBorder();
    }
}
