package duke.Command;

import duke.AppUi;
import duke.TaskList;
import duke.task.*;

import java.util.ArrayList;

public class TodoCommand extends Command {

    private static final int TODO = 0;
    private TaskList list;
    private AppUi appUi;

    public TodoCommand(String input, TaskList list) {
        super(input);
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute(){
        ArrayList<Todo> newTodos = Todo.of(input);
        if (newTodos == null) {
            return appUi.getDescriptionEmptyMsg();
        }

        newTodos.stream().forEach(list::add);

        return appUi.getAfterAddMsgVarargs(TODO, list.getSize(), newTodos.toArray(new Task[newTodos.size()]));
    }
}
