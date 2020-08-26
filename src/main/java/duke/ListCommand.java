package duke;

/**
 * Represents a list command for tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes listing of tasks.
     *
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printTaskList(taskList);
//        if (taskList.getTasks().isEmpty()) {
//            System.out.println(Ui.getLine());
//            System.out.println(Ui.getBot());
//            System.out.println("There are no tasks in your list yet! >_<");
//        } else {
//            System.out.println(Ui.getLine());
//            System.out.println(Ui.getBot());
//            System.out.println("Here are the tasks in your list:");
//            for (int i = 0; i < taskList.size(); i++) {
//                System.out.println(i + 1 + "." + " " + taskList.getTasks().get(i));
//            }
//        }
    }
}
