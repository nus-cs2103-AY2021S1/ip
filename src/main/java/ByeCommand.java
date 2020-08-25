public class ByeCommand extends Command {
    public static final String TAB = "  ";
    private static final String END = TAB + " Bye. Hope to see you again soon!";
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(END);
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
