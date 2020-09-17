package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a parser object that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the user input and gives the corresponding instructions.
     *
     * @param input User input.
     * @param taskList Duke's existing task list.
     * @param storage Duke's storage.
     * @param ui Duke's ui.
     */
    public String parse(String input, TaskList taskList, Storage storage, Ui ui) {
        storage.updateHardDisk(taskList.getTasks());
        String[] inputInfo = input.split(" ", 2);
        String command = inputInfo[0];
        try {
            switch (command) {
            case ("list"):
                return ui.printTasks(storage.loadExistingData());
            case ("help"):
                return ui.printHelp();
            case ("find"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To find a task, please enter 'find [keyword]'.\n");
                }
                assert inputInfo.length > 1;
                String keyword = inputInfo[1];
                return ui.printFind(storage.loadExistingData(), keyword);
            case ("bye"):
                return ui.printGoodbye();
            case ("done"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To mark a task as done, please enter 'done [task number]'.\n");
                }
                assert inputInfo.length > 1;
                String doneInfo = inputInfo[1];
                return handleDone(doneInfo, taskList);
            case ("delete"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To delete a task, please enter 'delete [task number]'.\n");
                }
                assert inputInfo.length > 1;
                String deleteInfo = inputInfo[1];
                return handleDelete(deleteInfo, taskList);
            case ("todo"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To add a todo, please enter 'todo [description]'.\n");
                }
                assert inputInfo.length > 1;
                String todoInfo = inputInfo[1];
                return handleToDo(todoInfo, taskList);
            case ("deadline"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date in yyyy-mm-dd format]'.\n");
                }
                assert inputInfo.length > 1;
                String deadlineInfo = inputInfo[1];
                return handleDeadline(deadlineInfo, taskList);
            case ("event"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To add an event, please enter 'event [description] /at [date in yyyy-mm-dd format]'.\n");
                }
                assert inputInfo.length > 1;
                String eventInfo = inputInfo[1];
                return handleEvent(eventInfo, taskList);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
            }
        } catch (DukeException e) {
            return new Ui(e.getMessage()).printMessage();
        } catch (IOException e) {
            return new Ui("An error occurred while retrieving the data.").printMessage();
        }
    }

    private String handleToDo(String info, TaskList taskList) {
        Task toDo = new ToDo(info);
        return taskList.addTask(toDo);
    }

    private String handleDeadline(String info, TaskList taskList) throws DukeException {
        String[] descriptionAndBy = info.split(" /by ", 2);
        if (descriptionAndBy.length < 2) {
            throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date in yyyy-mm-dd format]'.\n");
        } else {
            Task deadline = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
            return taskList.addTask(deadline);
        }
    }

    private String handleEvent(String info, TaskList taskList) throws DukeException{
        String[] descriptionAndAt = info.split(" /at ", 2);
        if (descriptionAndAt.length < 2) {
            throw new DukeException("Error! To add an event, please enter 'event [description] /at [date in yyyy-mm-dd format]'.\n");
        } else {
            Task event = new Event(descriptionAndAt[0], descriptionAndAt[1]);
            return taskList.addTask(event);
        }
    }

    private String handleDelete(String info, TaskList taskList) throws DukeException {
        try {
            int deleteIndex = Integer.valueOf(info) - 1;
            assert deleteIndex >= 0;
            assert deleteIndex < taskList.getTasks().size();
            return taskList.deleteTask(deleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.\n");
        }
    }

    private String handleDone(String info, TaskList taskList) throws DukeException {
        try {
            int doneIndex = Integer.valueOf(info) - 1;
            assert doneIndex >= 0;
            assert doneIndex < taskList.getTasks().size();
            return taskList.markDone(doneIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.\n");
        }
    }
}