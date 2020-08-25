import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String indentation = "     ";
    private static TaskManager taskManager;
    private static String dukeFilePath = "";

    public static void greet() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void exit() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(indentation + bye);
        System.out.println(horizontalLine);
    }

    public static void memoryProcessor(String[] data) {
        switch (data[0]) {
            case "T":
                Todo todo = new Todo(data[2]);
                if (data[1].equals("0")) {
                    todo.markAsDone();
                }
                taskManager.addTask(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(data[2], data[3]);
                if (data[1].equals("0")) {
                    deadline.markAsDone();
                }
                taskManager.addTask(deadline);
                break;
            case "E":
                Event event = new Event(data[2], data[3]);
                if (data[1].equals("0")) {
                    event.markAsDone();
                }
                taskManager.addTask(event);
                break;
        }

    }

    public static void commandProcessor(String command) throws DukeException {
        System.out.println(horizontalLine);
        if (command.equals("list")) {
            taskManager.printList();
        } else {
            String[] strArr = command.split(" ", 2);
            String taskType = strArr[0];
            if (taskType.equals("done")) {
                int taskNumber = Integer.parseInt(strArr[1]);
                taskManager.markTaskAsDone(taskNumber);
            } else if (taskType.equals("delete")) {
                int taskNumber = Integer.parseInt(strArr[1]);
                if (taskNumber <= taskManager.getNumOfTask()) {
                    taskManager.deleteTask(taskNumber);
                } else {
                    throw new DukeException(indentation + "☹ OOPS!!! You only have " + taskManager.getNumOfTask() + " tasks in your task list.");
                }
            } else if (taskType.equals("todo")) {
                if (strArr.length == 1) {
                    throw new DukeException(indentation + "☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(strArr[1]);
                    taskManager.addTask(todo);
                    taskManager.printAddedTask(todo);
                }
            } else if (taskType.equals("deadline")) {
                if (strArr.length == 1) {
                    throw new DukeException(indentation + "☹ OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] deadlineArr = strArr[1].split(" /by ", 2);
                    if (deadlineArr.length != 2) {
                        throw new DukeException(indentation + "☹ OOPS!!! Wrong format when describing a deadline.");
                    } else {
                        if (TimeParser.isValidTime(deadlineArr[1])) {
                            Deadline deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                            taskManager.addTask(deadline);
                            taskManager.printAddedTask(deadline);
                        } else {
                            throw new DukeException(indentation + "☹ OOPS!!! Wrong format when describing a date.");
                        }
                    }
                }
            } else if (taskType.equals("event")) {
                if (strArr.length == 1) {
                    throw new DukeException(indentation + "☹ OOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] eventArr = strArr[1].split(" /at ", 2);
                    if (eventArr.length != 2) {
                        throw new DukeException(indentation + "☹ OOPS!!! Wrong format when describing an event.");
                    } else {
                        if (TimeParser.isValidTime(eventArr[1])) {
                            Event event = new Event(eventArr[0], eventArr[1]);
                            taskManager.addTask(event);
                            taskManager.printAddedTask(event);
                        } else {
                            throw new DukeException(indentation + "☹ OOPS!!! Wrong format when describing a date.");
                        }
                    }
                }
            } else {
                throw new DukeException(indentation + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        System.out.println(horizontalLine);
    }

    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter(dukeFilePath);
            for (Task task : taskManager.getTaskList()) {
                String[] data = task.taskToArray();
                if(data.length == 3) {
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + System.lineSeparator());
                } else if (data.length == 4) {
                    writer.write(data[0] + "  " + data[1] + "  " + data[2] + "  " + data[3] + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static void main(String[] args) {
//        System.out.println("Hello from\n" + logo);
        taskManager = new TaskManager();
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();

            java.nio.file.Path path = java.nio.file.Paths.get(s, "src", "data");
            boolean directoryExists = java.nio.file.Files.exists(path);

            if(!directoryExists) {
                System.out.println("data folder does not exist. Let's create one.");
                File dir = new File(String.valueOf(path));
                dir.mkdir();
            }
            java.nio.file.Path filePath = java.nio.file.Paths.get(s, "src", "data", "duke.txt");
            dukeFilePath = String.valueOf(filePath);
            File f = new File(String.valueOf(filePath));
            if (!f.createNewFile()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String row = sc.nextLine();
                    String[] data = row.split(" {2}");
//                  System.out.println(Arrays.toString(data));
                    memoryProcessor(data);
                }
                sc.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Create new file please");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        greet();
        String command = scanner.nextLine();
        while (command != null) {
            try {
                if (command.equals("bye")) {
                    writeToFile();
                    exit();
                    scanner.close();
                    break;
                } else {
                    commandProcessor(command);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontalLine);
            }
            command = scanner.nextLine();
        }
    }
}
