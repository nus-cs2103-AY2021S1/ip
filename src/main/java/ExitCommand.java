public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
