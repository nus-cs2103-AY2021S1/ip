package ui;

import command.Command;
import mugexception.MugException;
import validator.Validator;
import tasks.TaskList;

/**
 * User interaction.
 */
public class Ui {

    /**
     * Read the input command.
     *
     * @param input User input.
     * @param tasks Task list.
     * @return Messages according to the user input.
     */
    public String readCommand(String input, TaskList tasks) {
        String output;

        try {
            String[] splitInput = input.split(" ", 2);
            // Validate Command
            Command command = Validator.command(splitInput[0]);

            switch (command) {
            case LIST:
                output = listAction(tasks);
                break;
            case BYE:
                output = byeAction();
                break;
            case DONE:
                output = doneAction(splitInput, tasks);
                break;
            case DELETE:
                output = deleteAction(splitInput, tasks);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                output = taskAction(command, splitInput, tasks);
                break;
            case FIND:
                output = findAction(splitInput, tasks);
                break;
            case UNDO:
                output = tasks.undoTask();
                break;
            default:
                output = "Hey!!! I'm sorry, but Mug don't know what that means :3";
                break;
            }
        } catch (MugException ex) {
            output = ex.getMessage();
        } catch (ArrayIndexOutOfBoundsException ex) {
            output = "There is Something wrong with your Storage.Storage";
        }

        return output;
    }

    /**
     * Undergoes action for done command.
     *
     * @param splitInput a split user input.
     * @param tasks A list of tasks to mark task done.
     * @return Respond after marking a task done.
     * @throws MugException If the user input is wrong format.
     */
    private String doneAction(String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(Command.DONE, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(Command.DONE, splitInput[1], false);
        // Extract and validate Id
        int doneTaskId = Validator.index(splitInput[1], splitInput.length);
        assert(doneTaskId > 0);
        return tasks.taskDone(doneTaskId);
    }

    /**
     * Undergoes action for delete command.
     *
     * @param splitInput a split user input.
     * @param tasks A list of tasks to remove task from.
     * @return Respond after deleting task.
     * @throws MugException If the user input is wrong format.
     */
    private String deleteAction(String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(Command.DELETE, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(Command.DELETE, splitInput[1], false);
        // Extract and Validate Id
        int deleteTaskId = Validator.index(splitInput[1], splitInput.length);
        assert(deleteTaskId > 0);
        return tasks.deleteTask(deleteTaskId);
    }

    /**
     * Undergoes action for add task(Todo, Deadline, Event) command.
     *
     * @param command Type of Task's command.
     * @param splitInput a split user input.
     * @param tasks A list of tasks to add in new tasks.
     * @return Respond after adding task.
     * @throws MugException If the user input is wrong format.
     */
    private String taskAction(Command command, String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(command, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(command, splitInput[1], false);
        // Extract Info
        String info = splitInput[1];
        return tasks.addTask(command, info);
    }

    /**
     * Undergoes action for find command.
     *
     * @param splitInput a split user input.
     * @param tasks A list of tasks to search.
     * @return Find results.
     * @throws MugException If the user input is wrong format.
     */
    private String findAction(String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(Command.FIND, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(Command.FIND, splitInput[1], false);
        // extract keyword
        String keyword = splitInput[1];
        return tasks.searchTask(keyword);
    }

    /**
     * Undergoes action for bye command.
     * @return Goodbye message.
     */
    private String byeAction() {
        return "** Bye. Hope to see you soon!! **";
    }

    /**
     * Undergoes action for list command.
     * @param tasks A list of tasks to show.
     * @return list of Tasks.
     */
    private String listAction(TaskList tasks) {
        return tasks.readList();
    }
}
