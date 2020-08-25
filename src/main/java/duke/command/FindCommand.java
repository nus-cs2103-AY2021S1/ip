package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Inherits from generic command class.
 */
public class FindCommand extends Command {
    protected boolean hasFoundTask = false;
    protected String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks matching the keyword.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     * @throws DukeException if keyword to search for is empty
     */
    @Override
    public void execute(taskListHandler handler, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        try {
            String keyword = input.split(" ")[1];
            for (Task t : handler.getTasks()) {
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
