package duke;

import duke.exception.DateException;
import duke.exception.DukeException;
import duke.exception.MissingInformationException;
import duke.task.Task;
import duke.format.DateFormat;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

/**
 * Represents a task managing system.
 * It makes use of different components to display UI,
 * keep track of tasks and manage the storage of data.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates an instance of Duke.
     * It initialises the UI, creates a storage based on the filepath
     * and initialises the existing tasks.
     * @param filePath The location where the task data is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DateException e) {
            ui.printMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the task management program.
     * It allows the user to input commands and executes
     * the commands accordingly, until the user chooses to
     * exit the program.
     */
    public void run() {
        ui.displayWelcome();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            Parser parser = new Parser(line);
            String command = parser.getCommand();
            try {
                if (command.equals("bye")) {
                    ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    ui.listTasks(tasks.getTasks());
                } else if (command.equals("done")) {
                    int index = parser.getIndex();
                    Task task = tasks.completeTask(index);
                    ui.completeSuccess(task);
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("delete")) {
                    int index = parser.getIndex();
                    Task task = tasks.deleteTask(index);
                    ui.deleteSuccess(task, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("todo")) {
                    String name = parser.getDescription(command);
                    Task todo = tasks.addTodo(name);
                    ui.addSuccess(todo, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("deadline")) {
                    String name = parser.getName(command);
                    Date by = parser.getDeadlineBy();
                    Task deadline = tasks.addDeadline(name, by);
                    ui.addSuccess(deadline, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("event")) {
                    String name = parser.getName(command);
                    Date at = parser.getEventAt();
                    Task event = tasks.addEvent(name, at);
                    ui.addSuccess(event, tasks.getCount());
                    storage.saveData(this.tasks.getTasks());
                } else if (command.equals("get")) {
                    String dateString = parser.getDate();
                    Date date = DateFormat.parseDate(dateString);
                    List<Task> tasksWithDate = tasks.getTasksWithDate(date);
                    ui.listTasksWithDate(tasksWithDate, dateString);
                } else if (command.equals("find")) {
                    String keyword = parser.getKeyWord();
                    List<Task> tasksWithWord = tasks.getTasksWithWord(keyword);
                    ui.listTasksWithWord(tasksWithWord);
                } else {
                    throw new DukeException("I am sorry, I don't know what that means :(");
                }
            } catch (DukeException | MissingInformationException | DateException | IOException e) {
                ui.printMessage(e.getMessage());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                ui.printMessage("Invalid task number!");
            }
        }
    }

    /**
     * Allows user to start running the program.
     * @param args Command line arguments which are not used for this program
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
