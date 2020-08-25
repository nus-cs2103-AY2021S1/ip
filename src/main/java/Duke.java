import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;
import duke.DukeIOException;
import duke.Storage;
import duke.UI.UI;
import duke.task.*;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        storage.addDirIfRequired();
        tasks = new TaskList(storage.load());
    }

    public void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
        }
    }

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

    public void handleToDo(String taskInfo) throws DukeInvalidCommandException{
        if (taskInfo.trim().equals("todo")) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = taskInfo.replace("todo", "").trim();
        tasks.addTask(new ToDos(taskInfo));
    }

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
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showIntro();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            handleTask(task);
        }
        sc.close();
    }

    public static String getFilePath() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke", "data", "tasks.text");
        return path.toString();
    }
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

