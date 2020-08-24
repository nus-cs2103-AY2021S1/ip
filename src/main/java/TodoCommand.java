import java.util.Scanner;

public class TodoCommand extends Command {
    public boolean execute() {
        ui.askTodo();
        String todoName = sc.nextLine();
        tm.add(new Todo(todoName));
        return true;
    }
}