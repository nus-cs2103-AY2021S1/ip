package duke;

import duke.task.Task;
import duke.exceptions.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManager {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private boolean terminate;

    public TaskManager() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.terminate = false;
    }

    public void manage() {
        try {
            // Try to load data from text file
            this.storage.loadFromTextFile(this.taskList);
        } catch (FileNotFoundException e) {
            // If text file does not exist yet, create the text file
            this.storage.createFile();
        }
        // Take in user input
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        while (!this.terminate) {
            String command = sc.nextLine();  // Read user input
            this.handleCommand(command); // Output user input
        }
        sc.close();
    }

    public void handleCommand(String command) {
        String[] splitCommand = this.parser.parse(command);
        try {
            try {
                Command enumCommand = Command.stringToEnum(splitCommand[0]);
                switch (enumCommand) {
                case LIST: {
                    this.taskList.list();
                    // break is necessary to prevent fall-through
                    break;
                }
                case BYE: {
                    this.terminate = true;
                    this.ui.printBye();
                    break;
                }
                case DONE: {
                    int index = Integer.parseInt(splitCommand[1]);
                    Task targetTask = taskList.get(index - 1);
                    String message = targetTask.completeTask();
                    try {
                        this.storage.modifyLineInTextFile(index, "done");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    this.ui.printDone(message);
                    break;
                }
                case DEADLINE: {
                    String[] splitDeadline = splitCommand[1].split(" /by ");
                    this.taskList.addDeadline("0", splitDeadline[0], splitDeadline[1]);
                    this.storage.writeToFile("D", "0", splitDeadline[0], splitDeadline[1]);
                    int size = taskList.getSize();
                    Task targetTask = taskList.get(size - 1);
                    this.ui.printTaskAdded(targetTask.toString(), size);
                    break;
                }
                case EVENT: {
                    String[] splitEvent = splitCommand[1].split(" /at ");
                    this.taskList.addEvent("0", splitEvent[0], splitEvent[1]);
                    this.storage.writeToFile("E", "0", splitEvent[0], splitEvent[1]);
                    int size = taskList.getSize();
                    Task targetTask = taskList.get(size - 1);
                    this.ui.printTaskAdded(targetTask.toString(), size);
                    break;
                }
                case TODO: {
                    if (splitCommand.length <= 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                    } else {
                        this.taskList.addTodo("0", splitCommand[1]);
                        this.storage.writeToFile("T", "0", splitCommand[1], "");
                        int size = taskList.getSize();
                        Task targetTask = taskList.get(size - 1);
                        this.ui.printTaskAdded(targetTask.toString(), size);
                    }
                    break;
                }
                case DELETE: {
                    int index = Integer.parseInt(splitCommand[1]);
                    Task targetTask = taskList.get(index - 1);
                    this.taskList.remove(index - 1);
                    try {
                        this.storage.modifyLineInTextFile(index, "delete");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int size = taskList.getSize();
                    this.ui.printTaskDeleted(targetTask.toString(), size);
                    break;
                }
                case INVALID: {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                }
            } catch (NumberFormatException error) {
                // When "done" is not followed by a valid number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (ArrayIndexOutOfBoundsException error) {
                // When "done" is not followed by any number
                throw new DukeException("OOPS!!! Please enter a valid index!");
            } catch (IndexOutOfBoundsException error) {
                // When "done is followed by a number that is out of range
                throw new DukeException("OOPS!!! That index is out of range!");
            } catch (DateTimeParseException e) {
                throw new DukeException("OOPS!!! Please enter the date in yyyy-mm-dd format!");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
    }
}
