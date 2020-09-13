package mug.ui;

import mug.command.Command;
import mug.mugexception.MugException;
import mug.storage.AddStorage;
import mug.storage.DeleteStorage;
import mug.storage.DoneStorage;
import mug.storage.Storage;
import mug.tasks.TaskList;
import mug.validator.Validator;


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
                output = tasks.undoTask(Storage.MUG_FILE, Storage.UNDO_FILE);
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

    private String doneAction(String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(Command.DONE, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(Command.DONE, splitInput[1], false);
        // Extract and validate Id
        int doneTaskId = Validator.index(splitInput[1], splitInput.length);
        assert(doneTaskId > 0);
        // write local Storage
        DoneStorage store = new DoneStorage(Storage.MUG_FILE);
        store.doneTask(doneTaskId);
        return tasks.taskDone(doneTaskId);
    }

    private String deleteAction(String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(Command.DELETE, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(Command.DELETE, splitInput[1], false);
        // Extract and Validate Id
        int deleteTaskId = Validator.index(splitInput[1], splitInput.length);
        assert(deleteTaskId > 0);
        // write local Storage
        DeleteStorage store = new DeleteStorage(Storage.MUG_FILE);
        store.deleteTask(deleteTaskId);
        return tasks.deleteTask(deleteTaskId);
    }

    private String taskAction(Command command, String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(command, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(command, splitInput[1], false);
        // Extract Info
        String info = splitInput[1];
        // write local storage
        AddStorage store = new AddStorage(Storage.MUG_FILE);
        store.appendTask(command, info);
        return tasks.addTask(command, info);
    }

    private String findAction(String[] splitInput, TaskList tasks) throws MugException {
        // Validate splitInput
        Validator.input(Command.FIND, splitInput.length, false);
        assert(splitInput.length > 1);
        Validator.info(Command.FIND, splitInput[1], false);
        // extract keyword
        String keyword = splitInput[1];
        return tasks.searchTask(keyword);
    }

    private String byeAction() {
        return "** Bye. Hope to see you soon!! **";
    }

    private String listAction(TaskList tasks) {
        return tasks.readList();
    }
}
