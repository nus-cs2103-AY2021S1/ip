package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.*;

public class AddCommand extends Command {
    private final Commands command;
    private final String input;

    public AddCommand(Commands command, String input) {
        this.command = command;
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch (this.command) {
            case TODO:
                Task newTodoTask = new Todo(this.input);
                addTask(newTodoTask, taskList, ui, storage);
                break;

            case DEADLINE:
                try {
                    String[] split = this.input.split(" /by ", 2);
                    Task newDeadlineTask = new Deadline(split[0], split[1]);
                    addTask(newDeadlineTask, taskList, ui, storage);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please enter a deadline to complete the task by " +
                            "or follow the exact command format!");
                }
                break;

            case EVENT:
                try {
                    String[] split = this.input.split(" /at ", 2);
                    Task newEventTask = new Event(split[0], split[1]);
                    addTask(newEventTask, taskList, ui, storage);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please enter the time at which the event will take place " +
                            "or follow the exact command format!");
                }
                break;

            default:
                throw new DukeException("Sorry! I don't recognize the type of task you're tyring to add!");
        }
    }

    public void addTask(Task newTask, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.addTask(newTask);
            storage.saveTask(newTask);
            String message = String.format("%s\nNow you have %d tasks in the list.",
                    newTask.toString(), taskList.size());
            ui.replyAdd(message);
        } catch (DukeException e) {
            throw new DukeException("I couldn't add the task to the database!");
        }
    }
}
