package main.java;

import main.java.duke.*;
import main.java.duke.task.Task;

import java.util.Scanner;

/**
 * Responsible for interpreting the input and interacting with the User.
 */
public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    /**
     * Initialised Duke with a designated location to read and save the data.
     * @param filePath File location to read and save data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Start the program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        int index;
        ui.showWelcome();
        outerLoop:
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] words = parser.interpretInput(input);
                String command = words[0];
                switch (command) {
                //Common functions
                case "bye":
                    storage.saveFile(taskList.toSaveFormat());
                    ui.showBye();
                    break outerLoop;
                case "done":
                    index = Integer.parseInt(words[1]);
                    taskList.doTask(index);
                    ui.showTaskDone(taskList.getTaskStatus(index));
                    break;
                case "list":
                    if (taskList.getTotalTask() == 0) {
                        ui.show("Currently, you have no tasks on hand");
                    } else {
                        ui.showTasks(taskList.toString());
                    }
                    break;
                //3 different types of task
                case "event":
                    try {
                        Task addedEvent = taskList.addEvent(words[1], words[2]);
                        ui.showTaskAdded(addedEvent.toString(), taskList.getTotalTask());
                    } catch (IndexOutOfBoundsException err) {
                        ui.showError("Error: Please key in as: \n" +
                                "event [title] /at YYYY-MM-DD [startTime] [endTime] where start and end time is in HH:MM ");
                    }
                    break;
                case "todo":
                    try {
                        Task addedToDo = taskList.addToDo(words[1]);
                        ui.showTaskAdded(addedToDo.toString(), taskList.getTotalTask());
                    } catch (IndexOutOfBoundsException err) {
                        ui.showError("Error: Please key in as: \n " +
                                "event [title]");
                    }
                    break;
                case "deadline":
                    try {
                        Task addedDeadline = taskList.addDeadLine(words[1], words[2]);
                        ui.showTaskAdded(addedDeadline.toString(), taskList.getTotalTask());
                    } catch (IndexOutOfBoundsException err) {
                        ui.showError("Error: Please key in as: \n " +
                                "event [title] /by YYYY-MM-DD HH:MM");
                    }
                    break;

                //Delete Task
                case "delete":
                    try {
                        index = Integer.parseInt(words[1]);
                        Task deletedTask = taskList.deleteTask(index);
                        ui.showDeletedTasks(deletedTask.toString());
                    } catch (NumberFormatException err) {
                        //echo("Error. Please key in an integer after \"done\"");
                    } catch (IndexOutOfBoundsException err) {
                        ui.showError("Key in \"delete [x]\" to delete x^th item");
                    }
                    break;

                //When command does not match any of those above
                default:
                    ui.showError("OOPS!!! I don't know what does it mean by: \"" + input + "\"");
                    break;
                }
            } catch (DukeException err) {
                ui.showError(err.getMessage());
            }
        }
    }

    /**
     * Driver for Duke.
     * @param args User Input.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}