public class ByeCommand extends Command {

    public ByeCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    public void execute(TaskList taskList) {
        System.out.println("-------------------------------------------\n" +
                "Bye bye! Hope to see you again soon!\n" +
                "-------------------------------------------");
        System.exit(0);
    }
}