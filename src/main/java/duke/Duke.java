package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import exception.*;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
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
                    isExit = true;
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
                        Parser.checkIndex(inputs, taskList.getSize());
                        int index = Integer.parseInt(inputs[1]) - 1;
                        ui.printDeleteTask(taskList.getTask(index), taskList.getSize() - 1);
                        taskList.removeTask(index);
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
