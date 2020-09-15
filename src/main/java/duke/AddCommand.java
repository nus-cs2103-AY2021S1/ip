package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Supports adding of tasks to the TaskList.
 */
public class AddCommand extends Command {
    private String input;

    /**
     * Instantiates AddCommand.
     *
     * @param input Input from user.
     */
    AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes command by adding the new task to TaskList
     * and write data to storage.
     *
     * @param tasks   TaskList containing the tasks.
     * @param storage To read and write to file.
     * @param ui      Interact with user.
     * @throws DukeException If input does not meet requirements.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        String[] inputArr = input.split(" ", 2);

        if (inputArr.length == 1) {
            throw new DukeException("Please key in a valid description");
        }

        String taskType = inputArr[0];
        String taskDescription = inputArr[1];

        Task newTask;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (taskType.equals(Instruction.TODO.getInstruction())) {
            newTask = toDoTask(taskDescription);
        } else if (taskType.equals(Instruction.DEADLINE.getInstruction())) {
            newTask = deadlineTask(taskDescription, formatter);
        } else if (taskType.equals(Instruction.EVENT.getInstruction())) {
            newTask = eventTask(taskDescription, formatter);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.getSize());
        storage.saveTasksToFile(tasks);
    }

    /**
     * Returns a ToDo task based on the task description given by user.
     *
     * @param taskDescription Description of the task to be done.
     * @return New ToDo task with given description.
     * @throws DukeException If no valid name is given.
     */
    private Task toDoTask(String taskDescription) throws DukeException {
        if (taskDescription.equals(Instruction.EMPTY.getInstruction())) {
            throw new DukeException("Please key in a valid name for To Do");
        }
        return new Todo(taskDescription);
    }

    /**
     * Returns a Deadline task based on task description given by user.
     *
     * @param taskDescription Description of the task to be done.
     * @param formatter Formats the date and time of the input.
     * @return New Deadline task with given description and formatted datetime.
     * @throws DukeException If description of task is invalid.
     */
    private Task deadlineTask(String taskDescription, DateTimeFormatter formatter) throws DukeException {
        String[] deadlineArr = taskDescription.split(" /by ", 2);

        if (deadlineArr.length != 2) {
            throw new DukeException("Please key in a valid name and date for the Deadline");
        }

        String deadlineName = deadlineArr[0];
        String deadlineDateTime = deadlineArr[1];

        if (deadlineDateTime.equals(Instruction.EMPTY.getInstruction())) {
            throw new DukeException("Please key in a valid date and time for the Deadline");
        }

        LocalDateTime localDateTime = LocalDateTime.parse(deadlineDateTime, formatter);
        return new Deadline(deadlineName, localDateTime);
    }

    /**
     * Returns an Event task based on task description given by user.
     *
     * @param taskDescription Description of the task to be done.
     * @param formatter Formats the date and time of the input.
     * @return New Event task with given description and formatted datetime.
     * @throws DukeException If description of task is invalid.
     */
    private Task eventTask(String taskDescription, DateTimeFormatter formatter) throws DukeException {
        String[] eventArr = taskDescription.split(" /at ", 2);

        if (eventArr.length != 2) {
            throw new DukeException("Please key in a valid name and date for the Event");
        }

        String eventName = eventArr[0];
        String eventDuration = eventArr[1];

        if (eventDuration.equals(Instruction.EMPTY.getInstruction())) {
            throw new DukeException("Please key in a valid duration for the Event");
        }

        LocalDateTime localDateTime = LocalDateTime.parse(eventDuration, formatter);
        return new Event(eventName, localDateTime);
    }

}
