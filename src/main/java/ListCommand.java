public class ListCommand extends Command {
    private final String TAB = "  ";
    private final String LISTTITLE = TAB + " Here are the tasks in your list:";
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        System.out.println(LISTTITLE);
        for (Task task : tasks.getTaskList()) {
            System.out.println(TAB + " " + i++ + "." + task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
