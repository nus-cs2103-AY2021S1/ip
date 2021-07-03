package bot.command;
import java.io.IOException;

import bot.Storage;
import bot.TaskList;
import bot.task.Deadline;
import bot.task.Event;
import bot.task.Todo;
import bot.util.InvalidInputException;

/**
 * A class that differentiates the different subtypes of Task and increments TaskList.
 */
public class AddCommand extends Command {
    private String name;
    private String date;

    /**
     * Constructor for the class that handles the addition of various tasks.
     *
     * @param cmd A string that is the keyword for command.
     * @param args variable number of arguments as different tasks require different number of arguments.
     * @throws IllegalArgumentException thrown if the number of argument is more than 2.
     */

    public AddCommand(String cmd, String... args) throws IllegalArgumentException {
        super(cmd);
        if (args.length == 1) {
            this.name = args[0];
        } else if (args.length == 2) {
            this.name = args[0];
            this.date = args[1];
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * A different subtype of Task is created depending on the command given.
     * This newly created Task is then added to taskList and updated in the storage.
     *
     * @param taskList The TaskList to be incremented.
     * @param storage The storage to store the new TaskLIst.
     * @return Response that the user will see.
     * @throws InvalidInputException Issues with writing to storage due to input command not recognised.
     * @throws IOException Issues with writing to storage file.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws InvalidInputException, IOException {
        try {
            switch(super.cmd) {
            case "deadline":
                Deadline newDeadline = new Deadline(this.name, this.date);
                taskList.add(newDeadline);
                storage.saveUserData(taskList);
                return "Got it. I've added this task:\n    "
                        + newDeadline + "\n    "
                        + "Now you have " + taskList.getSize() + " tasks in the list.";
            case "todo":
                Todo newTodo = new Todo(name);
                taskList.add(newTodo);
                storage.saveUserData(taskList);
                return "Got it. I've added this task:\n    "
                        + newTodo + "\n    "
                        + "Now you have " + taskList.getSize() + " tasks in the list.";
            case "event":
                Event newEvent = new Event(this.name, this.date);
                taskList.add(newEvent);
                storage.saveUserData(taskList);
                return "Got it. I've added this task:\n    "
                        + newEvent + "\n    " + "Now you have "
                        + taskList.getSize() + " tasks in the list.";
            default:
                throw new InvalidInputException("Sorry, I can't seem to save it.");
            }
        } catch (IOException e) {
            throw new IOException("Sorry, do what? Please give me a valid input."
                    + " Thank you.");
        }
    }
}
