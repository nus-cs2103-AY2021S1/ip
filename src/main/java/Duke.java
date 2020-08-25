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

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        outerLoop:
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = parser.interpretInput(input);
            String command = words[0];
            switch (command) {

            //Common functions
            case "bye":
                try {
                    storage.saveFile(taskList.toSaveFormat());
                    ui.showBye();
                    break outerLoop;
                } catch (DukeException err) {
                    ui.showError(err.getMessage());
                }
                break outerLoop;
            case "done":
                try {
                    int index = Integer.parseInt(words[1]);
                    taskList.doTask(index);
                    ui.showTaskDone(taskList.getTaskStatus(index));
                } catch (DukeException err) {
                    ui.showError(err.getMessage());
                }
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
                }  catch (DukeException err) {
                    ui.showError(err.getMessage());
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
                } catch (DukeException err) {
                    ui.showError(err.getMessage());
                }
                break;

            //Delete Task
            case "delete":
                try {
                    int index = Integer.parseInt(words[1]);
                    Task deletedTask = taskList.deleteTask(index);
                    ui.showDeletedTasks(deletedTask.toString());
                } catch (NumberFormatException err) {
                    //echo("Error. Please key in an integer after \"done\"");
                } catch (IndexOutOfBoundsException err) {
                    ui.showError("Key in \"delete [x]\" to delete x^th item");
                } catch (DukeException err) {
                    ui.showError(err.getMessage());
                }
                break;

            //When command does not match any of those above
            default:
                ui.showError("OOPS!!! I don't know what does it mean by: \"" + input + "\"" );
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}