package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

public class Parser {

    public void command(String str, TaskList tasks, Storage storage) throws DukeException {
        if (str.equals("list")) {
            tasks.displayTasks();
        } else {
            if (str.startsWith("done")) {
                tasks.completeTask(str);
                storage.save(tasks);
            } else if (str.startsWith("delete")) {
                tasks.deleteTask(str);
                storage.save(tasks);
            } else if (str.startsWith("todo") | str.startsWith("event") | str.startsWith("deadline")) {
                tasks.addTask(str);
                storage.save(tasks);
            } else {
                throw new InvalidCommandException();
            }
        }
    }
}
