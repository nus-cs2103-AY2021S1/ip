package duke.Command;

import duke.*;
import duke.Command.Command;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoC extends Command {
    private final String input;

    public TodoC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store){
        String result = "";
        String name = input.substring(5);
        try {
            if (name.isEmpty()) {
                throw new DukeException("no task indicated");
            } else {
                Todo td = new Todo(name);
                todoList.addTodo(td);
                result += "Aight new task for you: \n" + td.toString() + "\n";
                result += "Now you got " + todoList.size() +
                        " task(s) waiting man";
                store.write(td);

            }
        } catch (DukeException | IOException e) {
            result += "Enter a task after schedule!";
        }
        return result;
    }
}
