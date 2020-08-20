public class ListCommand extends Command {
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("here are the tasks in your list:\n")
                .append(Command.returnList());
        return sb.toString();
    }
}
