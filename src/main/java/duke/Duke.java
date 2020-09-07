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

    /**
     * Gets welcome message.
     * @return welcome message from Duke.
     */
    public String getWelcomeMessage() {
        return ui.gettWelcomeMessage();
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
            if (command.equals(Commands.BYE)) {
                try {
                    storage.writeData(taskList);
                } catch (IOException ex) {
                    return ex.getMessage();
                }
                return ui.getGoodbyeMessage();
            } else if (command.equals(Commands.LIST)) {
                return ui.getTaskList(taskList);
            } else if (command.equals(Commands.FIND)) {
                ArrayList<Task> results = inputs.length > 1 ? taskList.find(inputs[1]) : taskList.getTasks();
                return ui.getSearchResult(results);
            } else if (command.equals(Commands.DONE)) {
                try {
                    Parser.checkIndex(inputs, taskList.getSize());
                    assert inputs.length > 1;
                    int index = Integer.parseInt(inputs[1]) - 1;
                    taskList.markDone(index);
                    return ui.getCompletedTask(taskList.getTask(index));
                } catch (InvalidIndexException ex) {
                    return ex.getMessage();
                }
            } else if (command.equals(Commands.DELETE)) {
                try {
                    if (inputs.length > 1 && inputs[1].trim().equals("all")) {
                        taskList.removeAllTasks();
                        return ui.sayDeleteAllTasks();
                    } else {
                        Parser.checkIndex(inputs, taskList.getSize());
                        assert inputs.length > 1;
                        int index = Integer.parseInt(inputs[1]) - 1;
                        Task temp = taskList.getTask(index);
                        taskList.removeTask(index);
                        return ui.getDeletedTask(temp, taskList.getSize());
                    }
                } catch (InvalidIndexException ex) {
                    return ex.getMessage();
                }
            } else if (command.equals(Commands.TODO)) {
                try {
                    Parser.checkDescription(inputs, command);
                    assert inputs.length > 1;
                    taskList.addTask(new Todo(inputs[1]));
                    return ui.getAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                } catch (EmptyDescriptionException ex) {
                    return ex.getMessage();
                }
            } else if (command.equals(Commands.DEADLINE)) {
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
                    return ui.getAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                } catch (EmptyDescriptionException ex) {
                    return ex.getMessage();
                } catch (EmptyTimeException ex) {
                    return ex.getMessage();
                }
            } else {
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
                    return ui.getAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                } catch (EmptyDescriptionException ex) {
                    return ex.getMessage();
                } catch (EmptyTimeException ex) {
                    return ex.getMessage();
                }
            }
        } catch (InvalidCommandException ex) {
            return ex.getMessage();
        }
    }
}
