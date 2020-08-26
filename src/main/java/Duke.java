import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            String[] commandArr = line.split(" ", 2);
            String command = commandArr[0];
            try {
                if (command.equals("bye")) {
                    printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    listTasks();
                } else if (command.equals("done") || command.equals("delete")) {
                    handleCompleteStatus(command, commandArr);
                    saveData();
                } else if (command.equals("todo")) {
                    handleTask(TaskType.TODO, commandArr);
                    saveData();
                } else if (command.equals("deadline")) {
                    handleTask(TaskType.DEADLINE, commandArr);
                    saveData();
                } else if (command.equals("event")) {
                    handleTask(TaskType.EVENT, commandArr);
                    saveData();
                } else {
                    throw new DukeException("I am sorry, I don't know what that means :(");
                }
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
    }

    public static void loadData() {
        try {
            String FILE_PATH = "data/duke.txt";
            File dataFile = new File(FILE_PATH);
            if (!dataFile.exists()) {
                return;
            }
            FileReader reader = new FileReader(FILE_PATH);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                Task task = readTask(line);
                taskList.add(task);
                line = br.readLine();
            }
            reader.close();
        } catch (IOException e) {
            printMessage(e.getMessage());
        }

    }

    public static Task readTask(String line) {
        String[] taskComponent = line.split("\\|");
        boolean isComplete = !taskComponent[1].equals("0");
        String name = taskComponent[2];
        if (taskComponent[0].equals("T")) {
            return new Todo(name, isComplete);
        } else if (taskComponent[0].equals("D")) {
            String by = taskComponent[3];
            return new Deadline(name, isComplete, by);
        } else {
            String at = taskComponent[3];
            return new Event(name, isComplete, at);
        }
    }

    public static void saveData() {
        try {
            String DIR_PATH = "data";
            File directory = new File(DIR_PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String FILE_PATH = DIR_PATH + "/duke.txt";
            File dataFile = new File(FILE_PATH);
            dataFile.createNewFile();
            FileWriter writer = new FileWriter(FILE_PATH);
            String data = "";
            for (Task task : taskList) {
                data += String.format("%s\n", formatTask(task));
            }
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            printMessage(e.getMessage());
        }
    }

    public static String formatTask(Task task) {
        String name = task.getName();
        int isComplete = task.getStatus() ? 1 : 0;
        String type;
        if (task.getType() == TaskType.DEADLINE) {
            type = "D";
        } else if (task.getType() == TaskType.TODO) {
            type = "T";
        } else {
            type = "E";
        }
        if (type.equals("T")) {
            return String.format("%s|%d|%s", type, isComplete, name);
        } else {
            return String.format("%s|%d|%s|%s", type, isComplete, name, task.getDetails());
        }

    }

    public static void handleTask(TaskType taskType, String[] commandArr) {
        try {
            if (commandArr.length < 2 || commandArr[1].isBlank()) {
                throw new MissingInformationException(String.format("The description of a %s cannot be empty.", taskType.name().toLowerCase()));
            }
            if (taskType == TaskType.TODO) {
                String name = commandArr[1];
                addTodo(name);
            } else if (taskType == TaskType.DEADLINE) {
                String details = commandArr[1];
                String[] detailArr = details.split(" /by ", 2);
                if (detailArr.length < 2 || detailArr[1].isBlank()) {
                    throw new MissingInformationException("Deadline is missing a date.");
                }
                addDeadline(detailArr[0], detailArr[1]);
            } else {
                String details = commandArr[1];
                String[] detailArr = details.split(" /at ", 2);
                if (detailArr.length < 2 || detailArr[1].isBlank()) {
                    throw new MissingInformationException("Event is missing a date.");
                }
                addEvent(detailArr[0], detailArr[1]);
            }
        } catch (MissingInformationException e) {
            printMessage(e.getMessage());
        }
    }

    public static void handleCompleteStatus(String status, String[] commandArr) {
        try {
            if (commandArr.length < 2 || commandArr[1].isBlank()) {
                throw new MissingInformationException("Task number is missing!");
            }
            int index = Integer.valueOf(commandArr[1]);
            if (status.equals("done")) {
                completeTask(index);
            } else {
                deleteTask(index);
            }
        } catch (MissingInformationException e) {
            printMessage(e.getMessage());
        } catch (NumberFormatException e) {
            printMessage("Invalid task number!");
        }
    }

    public static void printMessage(String message) {
        System.out.println("    ______________________________________________________\n" +
                "     " + message + "\n    ______________________________________________________");
    }

    public static void addTodo(String name) {
        Todo todo = new Todo(name, false);
        addTask(todo);
    }

    public static void addDeadline(String name, String by) {
        Deadline deadline = new Deadline(name, false, by);
        addTask(deadline);
    }

    public static void addEvent(String name, String at) {
        Event event = new Event(name, false, at);
        addTask(event);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        int taskLen = taskList.size();
        String message = String.format("Got it. I have added this task:\n       %s\n     Now you have %d %s in the list.",
                task, taskLen, taskLen > 1 ? "tasks" : "task");
        printMessage(message);
    }

    public static void listTasks() {
        String output = "Here are the tasks in your list:\n";
        int taskLen = taskList.size();
        for (int i = 0; i < taskLen; i++) {
            output += String.format("     %d. %s", i + 1, taskList.get(i));
            if (i != taskLen - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }

    public static void completeTask(int index) {
        try {
            taskList.get(index - 1).markAsDone();
            String message = String.format("Nice! I've marked this task as done:\n       %s",
                    taskList.get(index - 1));
            printMessage(message);
        } catch (IndexOutOfBoundsException e) {
            printMessage(String.format("Task number %d is invalid!", index));
        }
    }

    public static void deleteTask(int index) {
        try {
            Task deletedTask = taskList.remove(index - 1);
            int taskLen = taskList.size();
            String message = String.format("Noted. I've removed this task:\n       %s\n     Now you have %d %s in the list.",
                    deletedTask, taskLen, taskLen > 1 ? "tasks" : "task");
            printMessage(message);
        } catch (IndexOutOfBoundsException e) {
            printMessage(String.format("Task number %d is invalid!", index));
        }
    }
}
