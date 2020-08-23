import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void printList(List<Task> tasks) throws DukeException {
        if(tasks.size() != 0) {
            // Dino lists out all items in list
            System.out.println("Dino lists your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + tasks.get(i));
            }
            System.out.println("To mark off a task after completion"
                    + ", input 'done <task number>'.");
        } else {
            throw new DukeException("Rawr! Dino could not find any items in your task list."
                    + "\nGet started by entering a task. "
                    + "Formats for a task can be found by entering 'format'.");
        }
    }

    public static void markDone(List<Task> tasks, String input) throws DukeException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber > tasks.size() || taskNumber < 1) {
            // task number is not valid
            throw new DukeException("Task " + taskNumber + " is not in your list of tasks!");
        } else {
            // Dino marks task as done
            int taskIndex = taskNumber - 1;
            Task doneTask = tasks.get(taskIndex);
            writeToFile(doneTask, DukeAction.MARK_DONE);
            doneTask.markAsDone();
            System.out.println("Great! Dino has marked " + "Task " + taskNumber
                    + " as done:\n" + doneTask);
        }
    }

    public static void addTask(List<Task> tasks, String input, String[] inputWords) throws DukeException {
        try {
            switch (inputWords[0]) {
            case "todo":
                String task = input.substring(5);
                Todo todo = new Todo(task);
                tasks.add(todo);
                writeToFile(todo, DukeAction.ADD);
                System.out.println("Dino has added to your list of tasks:\n"
                        + todo
                        + "\nNumber of tasks in list: " + tasks.size());
                break;
            case "deadline":
                String[] taskBy = input.substring(9)
                        .split("/by");
                if(taskBy.length >= 2 && !taskBy[0].equals("")) {
                    // condition checks that input has task description and date/time
                    StringBuilder taskDeadline = new StringBuilder(taskBy[1]);
                    // if there are more than one '/by' in input,
                    // task deadline taken as string after first '/by'
                    for(int i = 2; i < taskBy.length; i++) {
                        taskDeadline.append("/by").append(taskBy[i]);
                    }
                    Deadline deadline = new Deadline(taskBy[0], taskDeadline.toString());
                    tasks.add(deadline);
                    writeToFile(deadline, DukeAction.ADD);
                    System.out.println("Dino has added to your list of tasks:\n"
                            + deadline
                            + "\nNumber of tasks in list: " + tasks.size());
                } else {
                    throw new DukeException("Rawr! Dino could not add your task. "
                            + "Make sure your format is correct."
                            + "\nFormats to input a task can be found by entering 'format'.");
                }
                break;
            case "event":
                String[] eventAt = input.substring(6)
                        .split("/at");
                if(eventAt.length >= 2 && !eventAt[0].equals("")) {
                    // condition checks that input has task description and date/time
                    StringBuilder eventTime = new StringBuilder(eventAt[1]);
                    // if there are more than one '/at' in input,
                    // task deadline taken as string after first '/at'
                    for(int i = 2; i < eventAt.length; i++) {
                        eventTime.append("/at").append(eventAt[i]);
                    }
                    Event event = new Event(eventAt[0], eventTime.toString());
                    tasks.add(event);
                    writeToFile(event, DukeAction.ADD);
                    System.out.println("Dino has added to your list of tasks:\n"
                            + event
                            + "\nNumber of tasks in list: " + tasks.size());
                } else {
                    throw new DukeException("Rawr! Dino could not add your task. "
                            + "Make sure your format is correct."
                            + "\nFormats to input a task can be found by entering 'format'.");
                }
                break;
            default:
                // when input is not a deadline, event or todo
                throw new DukeException("Rawr! Dino could not add your task. "
                        + "Make sure your format is correct."
                        + "\nFormats to input a task can be found by entering 'format'.");
            }
        } catch(IndexOutOfBoundsException e) {
            // invalid task format entered
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    public static void deleteTask(List<Task> tasks, String input) throws DukeException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber > tasks.size() || taskNumber < 1) {
            // task number is not valid
            throw new DukeException("Task " + taskNumber + " is not in your list of tasks!");
        } else {
            // Dino deletes task from list
            int taskIndex = taskNumber - 1;
            Task toDelete = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            writeToFile(toDelete, DukeAction.DELETE);
            System.out.println("Rawr! Dino has deleted " + "Task " + taskNumber
                    + " from your list:\n" + toDelete
                    + "\nNumber of tasks in list: " + tasks.size());
        }
    }

    public static void processInputs(List<Task> tasks, String input, String[] inputWords) {
        try {
            if (input.equals("format")) {
                System.out.println("Formats for the three task types Todo, Deadline and Event,"
                        + " are shown below.\n"
                        + "Todo: 'todo <task description>'"
                        + " (e.g., visit new theme park)\n"
                        + "Deadline: 'deadline <task description>"
                        + " /by <date/time>' (e.g., submit report by 11/10/2019 5pm)\n"
                        + "Event: 'event <task description>"
                        + " /at <date/start and end time>' "
                        + "(e.g., team project meeting on 2/10/2019 2-4pm)\n"
                        + "\nAdditional Information:"
                        + "\nTo mark a task as done, input 'done <task number>'."
                        + "\nTo delete a task from your list, input 'delete <task number>'.");
            } else if (input.equals("list")) {
                printList(tasks);
            } else if (inputWords[0].equals("done") && inputWords.length == 2
                    && inputWords[1].matches("[0-9]+")) {
                // condition checks that user input is in the format "done X" where X is a numeric
                markDone(tasks, input);
            } else if (inputWords[0].equals("delete") && inputWords.length == 2
                    && inputWords[1].matches("[0-9]+")) {
                // condition checks that user input is in the format "delete X" where X is a numeric
                deleteTask(tasks, input);
            } else {
                // Dino adds task to list
                addTask(tasks, input, inputWords);
            }

        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("____________________________________________________________");
    }

    public static void loadData(List<Task> tasks) throws DukeException {
        //read Duke's data file and load tasks into tasks list
        try {
            File dir = new File("./data");
            File taskFile = new File("./data/tasklist.txt");
            if(dir.mkdir()) {
                System.out.println("Welcome. Dino has created a new directory " +
                        "to store your data.");
                if(taskFile.createNewFile()) {
                    System.out.println("Dino has successfully created a new file to store your task list.");
                } else {
                    System.out.println("Dino could not create a new file to store your task list.");
                }
            } else if(taskFile.createNewFile()) {
                System.out.println("Welcome. Dino has successfully created " +
                        "a new file to store your task list.");
            } else {
                Scanner scan = new Scanner(taskFile);
                while (scan.hasNext()) {
                    String taskTypeAndDone = scan.next();
                    String taskDesc = scan.nextLine().substring(1);
                    char taskType = taskTypeAndDone.charAt(1);
                    String taskDone = taskTypeAndDone.substring(4,5);
                    switch (taskType) {
                    case 'T':
                        Todo todo = new Todo(taskDesc);
                        if(taskDone.equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;

                    case 'D':
                        String[] taskBy = taskDesc.split("by:");
                        String task = taskBy[0].substring(0, taskBy[0].length() - 2);
                        String by = taskBy[1].substring(1, taskBy[1].length() - 1);
                        Deadline deadline = new Deadline(task, by);
                        if(taskDone.equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;

                    case 'E':
                        String[] eventAt = taskDesc.split("at:");
                        String eventTask = eventAt[0].substring(0, eventAt[0].length() - 2);
                        String at = eventAt[1].substring(1, eventAt[1].length() - 1);
                        Event event = new Event(eventTask, at);
                        if(taskDone.equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    }
                }
                System.out.println("Welcome back. Dino has successfully loaded your task data.");
            }
        } catch(IOException e) {
            throw new DukeException("Dino could not load your task data.");
        }
    }

    public static void run(List<Task> tasks) {
        try {
            loadData(tasks);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("____________________________________________________________");
    }

    public static void writeToFile(Task task, DukeAction action) throws DukeException {
        try {
            switch(action) {
            case ADD :
                FileWriter fw = new FileWriter("./data/tasklist.txt", true);
                fw.write(task.toString() + "\n");
                fw.close();
                System.out.println("Success!");
                break;

            case DELETE:
                File inputFile = new File("./data/tasklist.txt");
                File tempFile = new File("./data/myTempFile.txt");
                BufferedReader reader = new BufferedReader(new FileReader("./data/tasklist.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String lineToRemove = task.toString();
                String currentLine;
                while((currentLine = reader.readLine()) != null) {
                    if (!currentLine.equals(lineToRemove)) {
                        writer.write(currentLine);
                        writer.newLine();
                    }
                }
                writer.close();
                reader.close();
                System.gc();

                if(inputFile.delete()) {
                    if(tempFile.renameTo(inputFile)) {
                        System.out.println("Success!");
                    } else {
                        throw new DukeException("Dino could not write task data to hard disk.");
                    }
                } else {
                    throw new DukeException("Dino could not write task data to hard disk.");
                }
                break;

            case MARK_DONE:
                File taskFile = new File("./data/tasklist.txt");
                File temFile = new File("./data/myTemFile.txt");
                BufferedReader br = new BufferedReader(new FileReader("./data/tasklist.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temFile));

                String lineToMarkDone = task.toString();
                String currentL;
                while((currentL = br.readLine()) != null) {
                    if (!currentL.equals(lineToMarkDone)) {
                        bw.write(currentL);
                    } else {
                        String taskType = currentL.substring(0,3);
                        String taskDesc = currentL.substring(6);
                        bw.write(taskType + "[1]" + taskDesc);
                    }
                    bw.newLine();
                }
                bw.close();
                br.close();
                System.gc();

                if(taskFile.delete()) {
                    if(temFile.renameTo(taskFile)) {
                        System.out.println("Success!");
                    } else {
                        throw new DukeException("Dino could not write task data to hard disk.");
                    }
                } else {
                    throw new DukeException("Dino could not write task data to hard disk.");
                }
                break;
            }

        } catch(IOException e) {
            throw new DukeException("Dino could not write task data to hard disk.");
        }
    }

    public static void main(String[] args) {
        String logo = " ____\n"
                + "|  _ \\ _ _____  ___\n"
                + "| | | | |  _  |/   \\\n"
                + "| |_| | | | | | |_| |\n"
                + "|____/|_|_| |_|\\___/\n";
        System.out.println("Rawr! I'm Dino ><\n"
                +logo
                + "\nGet started on your task list by entering a task!"
                + "\nTo see how to format your task, input 'format'"
                + "\nTo see your list of tasks, input 'list'."
                + "\nTo mark a task as done, input 'done <task number>'."
                + "\nTo delete a task from your list, input 'delete <task number>'."
                + "\nTo stop Dino, input 'bye'."
                + "\n____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        List<Task> tasks = new ArrayList<>();
        run(tasks);

        while (!isBye && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputWords = input.split(" ");
            if (input.equals("bye")) {
                // Dino says bye
                System.out.println("Rawr. Hope to see you again soon! ><"
                        + "\n____________________________________________________________");
                isBye = true;
            } else {
                processInputs(tasks, input, inputWords);
            }
        }
        scanner.close();
    }
}