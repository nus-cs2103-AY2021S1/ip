package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoC extends Command {
    public void execute(Ui ui, TaskList todoList, Storage store){
        String name = ui.sc.nextLine();
        try {
            if (name.isEmpty()) {
                throw new DukeException("no task indicated");
            } else {
                Todo td = new Todo(name);
                todoList.addTodo(td);
                System.out.println("Aight new task for you: \n" + td.toString());
                System.out.println("Now you got " + todoList.size() +
                        " task(s) waiting man");
                List<String> arrayList = new ArrayList<>(Arrays.asList("a", "b", "c"));
                store.write(td);

            }
        } catch (DukeException | IOException e) {
            System.out.println("Enter a task after schedule!");
        }
    }
}
