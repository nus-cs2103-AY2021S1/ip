public class ErrorCommand extends Command {
    public boolean execute() {
        System.out.println("Oops, you gave an invalid command");
        return true;
    }
}