public class ListCommand extends Command {

    public ListCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    public void execute(TaskList taskList) {
        taskList.showList();
        System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.getTaskLength()) +
                TextUi.DIVIDER);
    }
}