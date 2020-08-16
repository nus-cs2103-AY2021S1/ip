package duke.Command;
import duke.Message;
import duke.Task;

public class ListCommand extends Command {

    @Override
    public String execute() {

        StringBuilder list = new StringBuilder();

        if (listArray.isEmpty()) {
            return "list is empty";
        } else {
            for (int i = 1; i <= listArray.size(); i++) {
                if (i == listArray.size()) {
                    list.append(i).append(".").append(listArray.get(i - 1).toString());
                } else {
                    list.append(i).append(".").append(listArray.get(i - 1).toString())
                            .append("\n").append(Message.INDENT);
                }
            }
            return "Here are the tasks in your list:\n" + Message.INDENT + list.toString();
        }
    }
}
