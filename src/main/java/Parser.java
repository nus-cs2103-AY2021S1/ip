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
                case ("find"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To find a task, please enter 'find [keyword]'.\n");
                } else {
                    String keyword = inputInfo[1];
                    return ui.printFind(storage.loadExistingData(), keyword);
                }
            case ("bye"):
                return ui.printGoodbye();
            case ("done"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To mark a task as done, please enter 'done [task number]'.\n");
                } else {
                    String info = inputInfo[1];
                    return handleDone(info, taskList, storage);
                }
            case ("delete"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To delete a task, please enter 'delete [task number]'.\n");
                } else {
                    String info = inputInfo[1];
                    return handleDelete(info, taskList, storage);
                }
            case ("todo"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To add a todo, please enter 'todo [description]'.\n");
                } else {
                    String info = inputInfo[1];
                    return handleToDo(info, taskList, storage);
                }
            case ("deadline"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date in yyyy-mm-dd format]'.\n");
                } else {
                    String info = inputInfo[1];
                    return handleDeadline(info, taskList, storage);
                }
            case ("event"):
                if (inputInfo.length < 2) {
                    throw new DukeException("Error! To add an event, please enter 'event [description] /at [date in yyyy-mm-dd format]'.\n");
                } else {
                    String info = inputInfo[1];
                    return handleEvent(info, taskList, storage);
                }
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
            }
        } catch (DukeException e) {
            return new Ui(e.getMessage()).printMessage();
        } catch (IOException e) {
            return new Ui("An error occurred while retrieving the data.").printMessage();
        }
    }

    private String handleToDo(String info, TaskList taskList, Storage storage) {
        Task toDo = new ToDo(info);
        return taskList.addTask(toDo);
    }

    private String handleDeadline(String info, TaskList taskList, Storage storage) throws DukeException {
        String[] descriptionAndBy = info.split(" /by ", 2);
        if (descriptionAndBy.length < 2) {
            throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date in yyyy-mm-dd format]'.\n");
        } else {
            Task deadline = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
            return taskList.addTask(deadline);
        }
    }

    private String handleEvent(String info, TaskList taskList, Storage storage) throws DukeException{
        String[] descriptionAndAt = info.split(" /at ", 2);
        if (descriptionAndAt.length < 2) {
            throw new DukeException("Error! To add an event, please enter 'event [description] /at [date in yyyy-mm-dd format]'.\n");
        } else {
            Task event = new Event(descriptionAndAt[0], descriptionAndAt[1]);
            return taskList.addTask(event);
        }
    }

    private String handleDelete(String info, TaskList taskList, Storage storage) throws DukeException {
        try {
            int deleteIndex = Integer.valueOf(info) - 1;
            return taskList.deleteTask(deleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.\n");
        }
    }

    private String handleDone(String info, TaskList taskList, Storage storage) throws DukeException {
        try {
            int doneIndex = Integer.valueOf(info) - 1;
            return taskList.markDone(doneIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.\n");
        }
    }
}