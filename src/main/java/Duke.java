import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    public static void intro() {
        System.out.println("\tHi handsome! My name is Duck. What can I do for you?");
    }
    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("\tGot it. I've added this task:" + "\n\t\t" + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }
    public static void printList() {
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, taskList.get(i)));
        }
    }

    public static void markTaskDone(String taskInfo) throws DukeIndexOutOfBoundsException{
        if (taskInfo.length() <= 5) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        int taskNo = Character.getNumericValue(taskInfo.charAt(5));
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to mark is invalid");
        }
        System.out.println("\tNice! I've marked this task as done:");
        int index = taskNo - 1;
        Task t = taskList.remove(index).doneTask();
        System.out.println("\t" + t);
        taskList.add(index, t);
    }

    public static void deleteTask(String taskInfo) throws DukeIndexOutOfBoundsException{
        if (taskInfo.length() <= 7) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int taskNo = Character.getNumericValue(taskInfo.charAt(7));
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new DukeIndexOutOfBoundsException("The task you want to delete is invalid");
        }
        int index = taskNo - 1;
        Task t = taskList.remove(index);
        System.out.println("\tNoted. I've removed this task:" + "\n\t\t" + t);
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
    }

    public static void handleToDo(String taskInfo) throws DukeInvalidCommandException{
        if (taskInfo.trim().equals("todo")) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = taskInfo.replace("todo", "").trim();
        Duke.addTask(new ToDos(taskInfo));
    }

    public static void handleDeadLine(String taskInfo) throws DukeInvalidCommandException{
        taskInfo = taskInfo.replace("deadline", "");
        String[] stringArr = taskInfo.split("/by", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String by = stringArr[1].trim();
        Duke.addTask(new Deadlines(taskInfo, by));
    }

    public static void handleEvent(String taskInfo) throws DukeInvalidCommandException{
        taskInfo = taskInfo.replace("event", "");
        String[] stringArr = taskInfo.split("/at", 2);
        if (stringArr.length != 2) {
            throw new DukeInvalidCommandException("The command is incomplete handsome :D");
        }
        taskInfo = stringArr[0].trim();
        String at = stringArr[1].trim();
        Duke.addTask(new Events(taskInfo, at));
    }

    public static void addDirIfRequired() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke");
        boolean dukeDirectoryExists = Files.exists(path);
        if (!dukeDirectoryExists) {
            File dir = new File(path.toString());
            dir.mkdir();
        }
        path = Paths.get(home, "Duke", "data");
        boolean dataDirectoryExists = Files.exists(path);
        if (!dataDirectoryExists) {
            File dir = new File(path.toString());
            dir.mkdir();
        }
    }

    public static void saveTaskList() throws DukeIOException{
        try {
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, "Duke", "data", "tasks.text");
            File file = new File(path.toString());
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                TaskType taskType = task.returnTaskType();
                char taskTypeChar = taskType.toString().charAt(1);
                int status = task.returnDoneStatus();
                String taskInfo = task.returnTaskInfo();
                String when = "";
                switch(taskTypeChar) {
                case 'D' :
                    Deadlines deadline = (Deadlines)taskList.get(i);
                    when = " : " + deadline.returnTime().trim();
                    break;
                case 'E' :
                    Events event = (Events)taskList.get(i);
                    when = " : " + event.returnTime().trim();
                    break;
                }
                String toWrite = taskType.toString().trim() + " : " + status + " : " + taskInfo.trim() + when;
                bw.write(toWrite);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeIOException("Sorry handsome but file is not found.");
        }
    }

    public static void bootUpDukeData() {
        try {
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, "Duke", "data", "tasks.text");
            File file = new File(path.toString());
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine == "") {
                    return;
                } else {
                    String[] strArr = nextLine.split(":");
                    String taskType = strArr[0];
                    char taskTypeChar = taskType.charAt(1);
                    String doneStatus = strArr[1].trim();
                    String taskInfo = strArr[2].trim();
                    String when = strArr.length > 3 ? strArr[3].trim() : "";
                    switch (taskTypeChar) {
                    case 'T' :
                        ToDos todo = Integer.parseInt(doneStatus) == Task.doneNo ?
                                new ToDos(taskInfo).doneTask() : new ToDos(taskInfo);
                        taskList.add(todo);
                        break;
                    case 'D' :
                        Deadlines deadline = Integer.parseInt(doneStatus) == Task.doneNo ?
                                new Deadlines(taskInfo, when).doneTask() : new Deadlines(taskInfo, when);
                        taskList.add(deadline);
                        break;
                    case 'E' :
                        Events event = Integer.parseInt(doneStatus) == Task.doneNo ?
                                new Events(taskInfo, when).doneTask() : new Events(taskInfo, when);
                        taskList.add(event);
                        break;
                    }
                }
            }
        } catch (IOException e) { /*A new file will be created when updatelist*/ }
    }

    public static void handleTask(String task) {
        switch(task) {
        case "bye" :
            System.out.print("\tBye. Hope to see you again soon!");
            return;
        case "list" :
            Duke.printList();
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
                    deleteTask(task);
                } else {
                    throw new DukeInvalidCommandException("Sorry handsome but I'm not sure about this command :)");
                }
                saveTaskList();
                break;
            } catch (DukeInvalidCommandException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeIndexOutOfBoundsException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeIncompleteCommandException err) {
                System.out.println("\t" + err.getMessage());
            } catch (DukeIOException err) {
                System.out.println("\t" + err.getMessage());
            }

        }
    }
    public static void main(String[] args) throws DukeRunTimeException, DukeCompileTimeException{
        Scanner sc = new Scanner(System.in);
        Duke.intro();
        addDirIfRequired();
        bootUpDukeData();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            handleTask(task);
        }
        sc.close();
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }
}

