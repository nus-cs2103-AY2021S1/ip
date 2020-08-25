package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Task(userInput);
        try {
            t.validate();

            String fileString = tasks.listToString();

            if (t.isTodo()) {
                // add to do to fileString
                ToDo todo = t.convertToTodo();
                tasks.add(todo);
                fileString += todo.taskToText() + "\n";

                // saves fileString to txt file
                Storage.save(Duke.FILENAME, fileString);

                // print template message
                System.out.println("    Got it. I've added this task:\n"
                        + "        " + todo + '\n'
                        + "    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.isDeadline()) {
                Deadline d = t.convertToDeadline();
                tasks.add(d);
                fileString += d.taskToText() + "\n";

                // saves fileString to txt file
                Storage.save(Duke.FILENAME, fileString);

                System.out.println("    Got it. I've added this task:\n"
                        + "        " + d + '\n'
                        + "    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.isEvent()) {
                Event e = t.convertToEvent();
                tasks.add(e);
                fileString += e.taskToText() + "\n";

                // saves fileString to txt file
                Storage.save(Duke.FILENAME, fileString);

                System.out.println("    Got it. I've added this task:\n"
                        + "        " + e + '\n'
                        + "    Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}

