package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import exception.*;

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
     * Runs Duke.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.printWelcome();

        while(sc.hasNextLine()) {
            String fullCommand = sc.nextLine();
            ui.printDivider();
            String[] inputs = fullCommand.split("\\s+", 2);
            try {
                Commands command = Parser.parse(fullCommand);
                if (command.equals(Commands.BYE)) {
                    ui.printGoodbye();
                    try {
                        storage.writeData(taskList);
                    } catch (IOException ex){
                        ui.printOutput(ex.getMessage());
                    }
                    break;
                } else if (command.equals(Commands.LIST)) {
                    ui.printTaskList(taskList);
                } else if (command.equals(Commands.DONE)) {
                    try {
                        Parser.checkIndex(inputs, taskList.getSize());
                        int index = Integer.parseInt(inputs[1]) - 1;
                        taskList.markDone(index);
                        ui.printCompleteTask(taskList.getTask(index));
                    } catch (InvalidIndexException ex) {
                        ui.printOutput(ex.getMessage());
                    }
                } else if (command.equals(Commands.DELETE)) {
                    try {
                        if (inputs.length > 1 && inputs[1].trim().equals("all")) {
                            taskList.removeAllTasks();
                            ui.printDeleteAllTasks();
                        } else {
                            Parser.checkIndex(inputs, taskList.getSize());
                            int index = Integer.parseInt(inputs[1]) - 1;
                            ui.printDeleteTask(taskList.getTask(index), taskList.getSize() - 1);
                            taskList.removeTask(index);
                        }
                    } catch (InvalidIndexException ex) {
                        ui.printOutput(ex.getMessage());
                    }
                } else if (command.equals(Commands.TODO)){
                    try {
                        Parser.checkDescription(inputs, command);
                        taskList.addTask(new Todo(inputs[1]));
                        ui.printAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                    } catch (EmptyDescriptionException ex) {
                        ui.printOutput(ex.getMessage());
                    }
                } else if (command.equals(Commands.DEADLINE)) {
                    try {
                        Parser.checkDescription(inputs, command);
                        String temp = " " + inputs[1];
                        String[] desc = temp.split("/by", 2);
                        inputs[1] = desc[0];
                        Parser.checkDescription(inputs, command);
                        Parser.checkTime(desc, command);
                        taskList.addTask(new Deadline(desc[0].trim(), desc[1].trim()));
                        ui.printAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                    } catch (EmptyDescriptionException ex) {
                        ui.printOutput(ex.getMessage());
                    } catch (EmptyTimeException ex) {
                        ui.printOutput(ex.getMessage());
                    }
                } else {
                    try {
                        Parser.checkDescription(inputs, command);
                        String temp = " " + inputs[1];
                        String[] desc = temp.split("/at", 2);
                        inputs[1] = desc[0];
                        Parser.checkDescription(inputs, command);
                        Parser.checkTime(desc, command);
                        taskList.addTask(new Event(desc[0].trim(), desc[1].trim()));
                        ui.printAddedTask(taskList.getTask(taskList.getSize() - 1), taskList.getSize());
                    } catch (EmptyDescriptionException ex) {
                        ui.printOutput(ex.getMessage());
                    } catch (EmptyTimeException ex) {
                        ui.printOutput(ex.getMessage());
                    }
                }
            } catch (InvalidCommandException ex) {
                ui.printOutput(ex.getMessage() + "hello");
            } finally {
                ui.printDivider();
            }
        }
    }
}
