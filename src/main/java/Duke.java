import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;
import duke.UI.UI;
import duke.storage.DukeIOException;
import duke.storage.Storage;
import duke.task.*;

/**
 * The class Duke denotes the faithful robot.
 *
 * @author Alvin Chee
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructs a Duke robot.
     *
     * @param filePath  FilePath to store the data file.
     */
    Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        storage.addDirIfRequired();
        tasks = new TaskList(storage.load());
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Marks task with task index in taskInfo as done.
     *
     * @param taskInfo  Description of task.
     */
    public void markTaskDone(String taskInfo) throws DukeIndexOutOfBoundsException{
        if (taskInfo.length() <= 5) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        taskInfo = taskInfo.replace("done", "").trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(taskInfo);
        } catch (NumberFormatException err){
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        System.out.println("\tNice! I've marked this task as done:");
        int index = taskNo - 1;
        Task task = tasks.remove(index).doneTask();
        System.out.println("\t" + task);
        tasks.add(index, task);
    }

    /**
     * Adds todos task to list of tasks.
     *
     * @param taskInfo  Description of todos task.
     */
    public void handleToDo(String taskInfo) throws DukeInvalidCommandException{
        if (taskInfo.trim().equals("todo")) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = taskInfo.replace("todo", "").trim();
        tasks.addTask(new ToDos(taskInfo));
    }

    /**
     * Adds deadline task to list of tasks.
     *
     * @param taskInfo  Description of deadline task.
     */
    public void handleDeadLine(String taskInfo) throws DukeInvalidCommandException, DukeDateTimeParseException{
        taskInfo = taskInfo.replace("deadline", "");
        String[] stringArr = taskInfo.split("/by", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String by = stringArr[1].trim();
        tasks.addTask(new Deadlines(taskInfo, by));
    }

    /**
     * Adds event task to list of tasks.
     *
     * @param taskInfo  Description of event task.
     */
    public void handleEvent(String taskInfo) throws DukeInvalidCommandException, DukeDateTimeParseException{
        taskInfo = taskInfo.replace("event", "");
        String[] stringArr = taskInfo.split("/at", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String at = stringArr[1].trim();
        tasks.addTask(new Events(taskInfo, at));
    }

    /**
     * Adds task to list of tasks based on type specified.
     *
     * @param task  Description of todos task.
     */
    public void handleTask(String task) {
        switch(task) {
        case "bye" :
            System.out.print("\tBye. Hope to see you again soon!");
            return;
        case "list" :
            printList();
            break;
        default :
            try {
                if (task.startsWith("done")) {
                    markTaskDone(task);
                } else if (task.startsWith("todo")) {
                    handleToDo(task);
                } else if (task.startsWith("deadline")) {
                    handleDeadLine(task);
                } else if (task.startsWith("event")) {
                    handleEvent(task);
                } else if (task.startsWith("delete")) {
                    tasks.deleteTask(task);
                } else {
                    throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                }
                storage.saveTaskList();
                break;
            } catch (DukeInvalidCommandException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeIndexOutOfBoundsException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeIncompleteCommandException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeIOException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeDateTimeParseException err) {
                System.out.println("\t" + err.getMessage());
            }
        }
    }

    /**
     * Bot introduces and gets input from user.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showIntro();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            handleTask(task);
        }
        sc.close();
    }

    /**
     * Gets file path based on user's system.
     */
    public static String getFilePath() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke", "data", "tasks.text");
        return path.toString();
    }

    /**
     * Executes all the operations stated.
     *
     * @param args  String arrays of operations.
     */
    public static void main(String[] args) throws DukeRunTimeException {
        new Duke(getFilePath()).run();
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }
}

