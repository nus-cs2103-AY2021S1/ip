import Task.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static final String LINE = "_______________________________________\n";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static File file;
    public static FileWriter writer;

    public static void main(String[] args) throws DukeException, IOException {

        // Opening
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        System.out.println(open);

        file = new File("data/duke.txt");
        readSavedData(file);
        writer = new FileWriter(file);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running) {
            String userInput = scanner.nextLine();
            String[] inputSplit = userInput.split(" ", 2);
            String userCommand = inputSplit[0];

            try {
                if (userCommand.equals("bye")) {  // For exiting the program
                    running = false;
                } else if (userCommand.equals("list")) {  // For viewing items in to do list
                    handleList();
                } else if (userCommand.equals("done")) {  // For marking items in the to do list as done
                    try {
                        handleDone(inputSplit[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(LINE + "Invalid input! Please specify which task you have completed! \n" + LINE);
                    }
                } else if (userCommand.equals("todo")) { // Add new to do task
                    try {
                        handleTodo(inputSplit[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(LINE + "Invalid input! Please specify your todo description! \n" + LINE);
                    }
                } else if (userCommand.equals("deadline")) { // Add new deadline
                    try {
                        handleDeadline(inputSplit[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(LINE + "Invalid input! Please specify your deadline description and details! \n" + LINE);
                    }

                } else if (userCommand.equals("event")) { // Add new event
                    try {
                        handleEvent(inputSplit[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(LINE + "Invalid input! Please specify your event description and details! \n" + LINE);
                    }

                } else if (userCommand.equals("delete")) { // Delete task
                    try {
                        handleDelete(inputSplit[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(LINE + "Invalid input! Please specify which task you want to delete! \n" + LINE);
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        // Closing
        saveData();
        scanner.close();
        writer.close();
        String close = "_______________________________________ \n"
                + "Goodbye! See you soon! \n"
                + "_______________________________________ \n";
        System.out.println(close);
    }

    public static void handleList() {
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            output = output + i + ". " + taskList.get(i - 1) + "\n";
        }
        System.out.println(LINE + "Here are the tasks in your list: \n" + output + LINE);
    }

    public static void handleDone(String taskIdString) throws DukeException {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <= 0 || taskId > taskList.size()) {
            throw new DukeException(LINE + "Invalid input! That task does not exist! \n" + LINE);
        } else {
            taskList.get(taskId - 1).setCompleted();
            System.out.println(LINE + "Nice! I've marked this task as done: \n"
                    + taskList.get(taskId - 1) + "\n" + LINE);
        }
    }

    public static void handleDelete(String taskIdString) {
        int taskId = Integer.parseInt(taskIdString);
        if (taskId <=0 || taskId > taskList.size()) {
            System.out.println(LINE + "Invalid input! That task does not exist! \n" + LINE);
        } else {
            int new_size = taskList.size() - 1;
            System.out.println(LINE + "Noted! I've deleted this task: \n"
                    + taskList.get(taskId - 1) + "\n"
                    + "Now you have " + new_size + " tasks in the list."
                    + "\n" + LINE);
            taskList.remove(taskId - 1);
        }
    }

    public static void handleTodo(String todoDescription) throws IOException {
        ToDo newToDo = new ToDo(todoDescription, false);
        taskList.add(newToDo);
        String output = LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleDeadline(String deadlineDetails) throws IOException {
        String[] details = deadlineDetails.split(" /by ", 2);
        Deadline newDeadline = new Deadline(details[0], details[1], false);
        taskList.add(newDeadline);
        String output = LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void handleEvent(String eventDetails) throws IOException {
        String[] details = eventDetails.split(" /at ", 2);
        Event newEvent = new Event(details[0], details[1], false);
        taskList.add(newEvent);
        String output = LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + LINE;
        System.out.println(output);
    }

    public static void readSavedData(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] taskSplit = task.split(">", 4);
                String taskType = taskSplit[0];
                boolean taskIsDone = taskSplit[1].equals("1") ? true : false;
                String taskDescription = taskSplit[2];
                if (taskType.equals("T")) {
                    taskList.add(new ToDo(taskDescription, taskIsDone));
                } else if (taskType.equals("D")) {
                    String taskBy = taskSplit[3];
                    taskList.add(new Deadline(taskDescription, taskBy, taskIsDone));
                } else {
                    String taskAt = taskSplit[3];
                    taskList.add(new Event(taskDescription, taskAt, taskIsDone));
                }
            }
            fileScanner.close();
        }
    }

    public static void saveData() throws IOException {
        for (Task task: taskList) {
            writer.write(task.toEncoding() + System.lineSeparator());
        }
    }
}
