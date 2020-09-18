package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import exception.EmptyDescriptionException;
import exception.EmptyTimeException;
import exception.InvalidCommandException;
import exception.InvalidIndexException;

/**
 * Represents Duke, a chat bot that allows tasks management (add, delete, mark as done).
 * @author Lucia Tirta Gunawan
 * @author A0200718N
 */
public class Duke {
    /** Storage to load and save tasks. */
    private Storage storage;
    /** List of tasks saved in Duke. */
    private TaskList taskList;
    /** User interface to display output from Duke. */
    private Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath the file path of the saved tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }

    private String getDoneResponse(String[] inputs) {
        try {
            Parser.checkIndex(inputs, taskList.getSize());
            assert inputs.length > 1;
            int index = Integer.parseInt(inputs[1]) - 1;
            taskList.markDone(index);
            storage.writeData(taskList);
            return ui.getCompletedTask(taskList.getTask(index));
        } catch (InvalidIndexException | IOException ex) {
            return ex.getMessage();
        }
    }

    private String getDeleteResponse(String[] inputs) {
        try {
            if (inputs.length > 1 && inputs[1].trim().equals("all")) {
                taskList.removeAllTasks();
                storage.writeData(taskList);
                return ui.getDeleteAllTasksMessage();
            } else {
                Parser.checkIndex(inputs, taskList.getSize());
                assert inputs.length > 1;
                int index = Integer.parseInt(inputs[1]) - 1;
                Task temp = taskList.getTask(index);
                taskList.removeTask(index);
                storage.writeData(taskList);
                return ui.getDeletedTask(temp, taskList.getSize());
            }
        } catch (InvalidIndexException | IOException ex) {
            return ex.getMessage();
        }
    }

    private String getAddTodoResponse(String[] inputs, Commands command) {
        try {
            Parser.checkDescription(inputs, command);
            assert inputs.length > 1;
            taskList.addTask(new Todo(inputs[1]));
            storage.writeData(taskList);
            return ui.getAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
        } catch (EmptyDescriptionException | IOException ex) {
            return ex.getMessage();
        }
    }

    private String getAddEventResponse(String[] inputs, Commands command) {
        try {
            Parser.checkDescription(inputs, command);
            assert inputs.length > 1;
            String temp = " " + inputs[1];
            String[] taskInfo = temp.split("/at", 2);
            assert taskInfo.length > 0;
            inputs[1] = taskInfo[0];
            Parser.checkDescription(inputs, command);
            Parser.checkTime(taskInfo, command);
            assert taskInfo.length > 1;
            taskList.addTask(new Event(taskInfo[0].trim(), taskInfo[1].trim()));
            storage.writeData(taskList);
            return ui.getAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
        } catch (EmptyDescriptionException | EmptyTimeException | IOException ex) {
            return ex.getMessage();
        }
    }

    private String getAddDeadlineResponse(String[] inputs, Commands command) {
        try {
            Parser.checkDescription(inputs, command);
            assert inputs.length > 1;
            String temp = " " + inputs[1];
            String[] taskInfo = temp.split("/by", 2);
            assert taskInfo.length > 0;
            inputs[1] = taskInfo[0];
            Parser.checkDescription(inputs, command);
            Parser.checkTime(taskInfo, command);
            assert taskInfo.length > 1;
            taskList.addTask(new Deadline(taskInfo[0].trim(), taskInfo[1].trim()));
            storage.writeData(taskList);
            return ui.getAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
        } catch (EmptyDescriptionException | EmptyTimeException | IOException ex) {
            return ex.getMessage();
        }
    }

    String getAttachTagResponse(String input) {
        try {
            String[] inputs = input.split("\\s+", 3);
            Parser.checkIndex(inputs, taskList.getSize());
            assert inputs.length > 1;
            int index = Integer.parseInt(inputs[1]) - 1;
            String tag = inputs.length > 2 ? inputs[2] : "";
            taskList.tagTask(index, tag);
            storage.writeData(taskList);
            return ui.getTagTaskMessage(taskList.getTask(index));
        } catch (InvalidIndexException | IOException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Gets welcome message.
     * @return welcome message from Duke.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Gets response from Duke after giving command
     * @param input the user input
     * @return the String response from Duke
     */
    public String getResponse(String input) {
        // ui.printWelcome();
        String[] inputs = input.split("\\s+", 2);
        try {
            Commands command = Parser.parse(input);
            String response;
            switch (command) {
            case BYE:
                response = ui.getGoodbyeMessage();
                break;
            case LIST:
                response = ui.getTaskList(taskList);
                break;
            case FIND:
                ArrayList<Task> results = inputs.length > 1 ? taskList.find(inputs[1]) : taskList.getTasks();
                response = ui.getSearchResult(results);
                break;
            case DONE:
                response = getDoneResponse(inputs);
                break;
            case DELETE:
                response = getDeleteResponse(inputs);
                break;
            case TODO:
                response = getAddTodoResponse(inputs, command);
                break;
            case DEADLINE:
                response = getAddDeadlineResponse(inputs, command);
                break;
            case EVENT:
                response = getAddEventResponse(inputs, command);
                break;
            case TAG:
                response = getAttachTagResponse(input);
                break;
            default:
                response = "";
                break;
            }
            return response;
        } catch (InvalidCommandException ex) {
            return ex.getMessage();
        }
    }
}
