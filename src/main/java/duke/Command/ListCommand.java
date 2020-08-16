package duke.Command;
import duke.Message;

public class ListCommand extends Command {

    @Override
    public String execute(String string) {
        return Message.MESSAGE_LIST;
    }
}
