/**
 * ListCommand inherits from Command and is called to print
 * the todo items when user inputs "list".
 */
class ListCommand extends Command {

    /**
     * Used to print the current TaskList to the user.
     *
     * @param tasks This is the saved TaskList in saved file.
     * @return A boolean to indicate whether the program should be exited.
     */
    @Override
    boolean execute(TaskList tasks) {
        Ui.printTasks(tasks.getTasksDescription(), Action.LIST);
        return false;
    }
}