package bot.command;

import bot.Storage;
import bot.TaskList;
import bot.task.Deadline;
import bot.task.Event;
import bot.task.Todo;
import bot.util.InvalidInputException;

import java.io.IOException;

public class AddCommand extends Command {
    private String name;
    private String date;

    public AddCommand(String cmd, String... args) throws IllegalArgumentException{
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

    @Override
    public String run(TaskList taskList, Storage storage) throws InvalidInputException, IOException {
        try{
            switch(super.cmd) {
                case "deadline":
                    Deadline newDeadline = new Deadline(this.name, this.date);
                    taskList.add(newDeadline);
                    storage.saveUserData(taskList);
                    return "Got it. I've added this task:\n    " +
                            newDeadline + "\n    " +
                            "Now you have " + taskList.getSize() + " tasks in the list.";
                case "todo":
                    Todo newTodo = new Todo(name);
                    taskList.add(newTodo);
                    storage.saveUserData(taskList);
                    return "Got it. I've added this task:\n    " +
                            newTodo + "\n    " +
                            "Now you have " + taskList.getSize() + " tasks in the list.";
                case "event":
                    Event newEvent = new Event(this.name, this.date);
                    taskList.add(newEvent);
                    storage.saveUserData(taskList);
                    return "Got it. I've added this task:\n    " +
                            newEvent + "\n    " + "Now you have " +
                            taskList.getSize() + " tasks in the list.";
                default:
                    throw new InvalidInputException("Sorry, I can't seem to save it.");
            }
        } catch (IOException e) {
            throw new IOException("Sorry, do what? Please give me a valid input." +
                    " Thank you.");
        }

    }
}
