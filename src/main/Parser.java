import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

public class Parser {
    public void parse(String input, TaskList taskList, Storage storage, Ui ui, Scanner sc) {
        String[] inputInfo = input.split(" ", 2);
        String command = inputInfo[0];
        try {
            switch (command) {
                case ("list"):
                    ui.printTasks(storage.loadExistingData());
                    break;
                case ("bye"):
                    ui.printGoodbye();
                    System.exit(0);
                    break;
                case ("done"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To mark a task as done, please enter 'done [task number]'.\n");
                    } else {
                        String info = inputInfo[1];
                        handleDone(info, taskList, storage);
                    }
                    break;
                case ("delete"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To delete a task, please enter 'delete [task number]'.\n");
                    } else {
                        String info = inputInfo[1];
                        handleDelete(info, taskList, storage);
                    }
                    break;
                case ("todo"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To add a todo, please enter 'todo [description]'.\n");
                    } else {
                        String info = inputInfo[1];
                        handleToDo(info, taskList, storage);
                    }
                    break;
                case ("deadline"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date in yyyy-mm-dd format]'.\n");
                    } else {
                        String info = inputInfo[1];
                        handleDeadline(info, taskList, storage);
                    }
                    break;
                case ("event"):
                    if (inputInfo.length < 2) {
                        throw new DukeException("Error! To add an event, please enter 'event [description] /at [date in yyyy-mm-dd format]'.\n");
                    } else {
                        String info = inputInfo[1];
                        handleEvent(info, taskList, storage);
                    }
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occured while retrieving the data.");
        }
    }

    public void handleToDo(String info, TaskList taskList, Storage storage) {
        Task toDo = new ToDo(info);
        taskList.addTask(toDo);
        storage.updateHardDisk(taskList.getTasks());
    }

    public void handleDeadline(String info, TaskList taskList, Storage storage) throws DukeException {
        String[] descriptionAndBy = info.split(" /by ", 2);
        if (descriptionAndBy.length < 2) {
            throw new DukeException("Error! To add a deadline, please enter 'deadline [description] /by [date in yyyy-mm-dd format]'.\n");
        } else {
            Task deadline = new Deadline(descriptionAndBy[0], descriptionAndBy[1]);
            taskList.addTask(deadline);
            storage.updateHardDisk(taskList.getTasks());
        }
    }

    public void handleEvent(String info, TaskList taskList, Storage storage) throws DukeException{
        String[] descriptionAndAt = info.split(" /at ", 2);
        if (descriptionAndAt.length < 2) {
            throw new DukeException("Error! To add an event, please enter 'event [description] /at [date in yyyy-mm-dd format]'.\n");
        } else {
            Task event = new Event(descriptionAndAt[0], descriptionAndAt[1]);
            taskList.addTask(event);
            storage.updateHardDisk(taskList.getTasks());
        }
    }

    public void handleDelete(String info, TaskList taskList, Storage storage) throws DukeException {
        try {
            int deleteIndex = Integer.valueOf(info) - 1;
            taskList.deleteTask(deleteIndex);
            storage.updateHardDisk(taskList.getTasks());
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.\n");
        }
    }

    public void handleDone(String info, TaskList taskList, Storage storage) throws DukeException {
        try {
            int doneIndex = Integer.valueOf(info) - 1;
            taskList.markDone(doneIndex);
            storage.updateHardDisk(taskList.getTasks());
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! This task does not exist, please enter a valid task number.\n");
        }
    }
}