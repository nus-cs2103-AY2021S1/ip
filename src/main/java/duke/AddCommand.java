package duke;

import java.util.ArrayList;

/**
 * Controls logic of adding tasks.
 */
class AddCommand extends Command {

    /** Name of command e.g. Todo. */
    private CommandName commandName;
    /** Description of task. */
    private String description;
    /** Date in string format. */
    private String date;

    AddCommand(CommandName commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }

    AddCommand(CommandName commandName, String description, String date) {
        this.commandName = commandName;
        this.description = description;
        this.date = date;
    }

    /**
     * Executes adding tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     * @throws DukeException When date time in wrong format, or description not given,
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] taskAndTaskListInfo = tasks.addTask(commandName, description, date);
        String taskString = taskAndTaskListInfo[0];
        String lenString = taskAndTaskListInfo[1];

        ArrayList<Task> taskList = tasks.getTasks();
        storage.save(taskList);
        return ui.showAddTaskMessage(taskString, lenString);

    }

    /**
     * Checks if should exit program.
     *
     * @return Should not exit program.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
