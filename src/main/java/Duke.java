import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String delete_task_number_missing = "Please enter a valid task number";
    private static final String dash = ("\u2500").repeat(5);
    public static final String line = "//";
    private static final String greeting = "Hello! I'm Duke \n" +
            "What can I do for you?";
    private static final String task_read = "Here are the tasks in your list: \n";
    private static final String task_added = "Got it. I've added this task: \n";
    private static final String task_completed = "Nice! I've marked this task as complete. \n";
    private static final String farewell = "Bye. Hope to see you again soon.";
    private static final String task_index_out_of_bounds = "That task does not exist.";
    private static final String task_number_format = "Please enter the task number in numerals.";
    public static final String task_no_description = "Invalid, no task description provided.";
    public static final String task_invalid_type = "Invalid, not an accepted task type";
    public static final String task_list_number = "\nNow you have %d tasks in the list";
    public static final String task_removed = "Noted. I've removed this task";
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_LOCATION = FILE_DIRECTORY + "duke.txt";
    private boolean running = true;
    private List<Task> taskList = new ArrayList<>(100);

    public Duke() {
    }

    public static void main(String[] args) {
        try {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            Duke servingDuke = new Duke();
            System.out.println(servingDuke.constructMessage(greeting));

            //Create directory if it does not exist
            File fileDirectory = new File(FILE_DIRECTORY);
            boolean directoryIsCreated = fileDirectory.mkdirs();

            //Create file and copy contents into list
            File fileDatabase = new File(FILE_LOCATION);
            boolean fileIsCreated = fileDatabase.createNewFile();
            servingDuke.retrieveFromDatabase(fileDatabase);

            //Create new FileWriter to write to file
            FileWriter fw = new FileWriter(FILE_LOCATION, true);
//            File inputFile = new File("text-ui-test/input.txt");
            //Manage input from user
            Scanner sc = new Scanner(System.in);
            while (servingDuke.canRun()) {
                String input = sc.nextLine();
                servingDuke.respondToInput(input);
            }
            sc.close();
            servingDuke.saveData(fw);
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    private void saveData(FileWriter fw) throws IOException {
        for (Task t : taskList) {
            fw.write(t.generateSaveFormat()+ "\n");
//            System.out.println(t.generateSaveFormat());
        }
        fw.close();
    }

    private void retrieveFromDatabase(File fileDatabase) throws FileNotFoundException {
        Scanner s = new Scanner(fileDatabase);
        while (s.hasNext()) {
            Task next = generateTask(s.nextLine());
//            System.out.println(next);
            taskList.add(next);
        }
        s.close();
    }

    private Task generateTask(String nextLine) {
        String[] taskCharacteristics = nextLine.split(line);
//        for (int i = 0; i < taskCharacteristics.length; i++) {
//            System.out.println(i + ": " + taskCharacteristics[i]);
//        }
        boolean isDone = taskCharacteristics[1].equals("1");
        String desc = taskCharacteristics[2];
        switch (taskCharacteristics[0]) {
            case ("T"):
                return new Todo(desc);
            case ("D"):
                return new Deadline(desc, LocalDate.parse(taskCharacteristics[3]));
            case ("E"):
                return new Event(desc, LocalDate.parse(taskCharacteristics[3]));
            default:
                return new Task(desc);
        }
    }

    private void respondToInput(String rawInput) {
        //Remove leading and trailing whitespaces
        String input = rawInput.trim();
        String[] breakdown = input.split(" ", 2);
        if (!input.isEmpty()) {
            if (input.equals("bye")) {
                running = false;
                System.out.println(
                        constructMessage(farewell));
            } else if (input.equals("list")) {
                System.out.println(
                        constructMessage(task_read + printAsString(taskList)));
            } else if (breakdown[0].equals("delete")) {
                deleteTask(breakdown);
            } else if (breakdown[0].equals("done")) {
                markTaskAsDone(breakdown);
            } else {
                addTask(breakdown);
            }
        }
    }

    private void deleteTask(String[] breakdown) {
        try {
            int taskNumber = Integer.parseInt(breakdown[1]);
            Task deletedTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            String count_text = String.format(task_list_number, taskList.size());
            System.out.println(constructMessage(
                    task_removed + deletedTask + count_text

            ));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(constructMessage(delete_task_number_missing));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_index_out_of_bounds));
        } catch (NumberFormatException e) {
            System.out.println(constructMessage(task_number_format));
        }
    }

    private void addTask(String[] breakdown) {
        try {
            String taskType = breakdown[0];
            if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
                throw new DukeException(taskType);
            }
            String description = breakdown[1];
            Task newTask = null;
            switch (taskType) {
                case "todo":
                    newTask = new Todo(description);
                    break;
                case "deadline":
                    String[] deadlineDetails = description.split("/by", 2);
                    String deadlineDesc = deadlineDetails[0].trim();
                    LocalDate deadlineTime = LocalDate.parse(deadlineDetails[1].trim());
                    newTask = new Deadline(deadlineDesc, deadlineTime);
                    break;
                case "event":
                    String[] eventDetails = description.split("/at", 2);
                    String eventDesc = eventDetails[0].trim();
                    LocalDate eventTime = LocalDate.parse(eventDetails[1].trim());
                    newTask = new Event(eventDesc, eventTime);
                    break;
            }
            taskList.add(newTask);
            String count_text = String.format(task_list_number, taskList.size());
            System.out.println(constructMessage(
                    task_added + newTask.toString() + count_text
            ));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_no_description));
        } catch (DukeException e) {
            System.err.println(constructMessage(e.toString()));
        }
    }

    private void markTaskAsDone(String[] breakdown) {
        try {
            int taskNumber = Integer.parseInt(breakdown[1]);
            Task completedTask = taskList.get(taskNumber - 1);
            completedTask.markDone();
            System.out.println(constructMessage(
                    task_completed + completedTask.toString()
            ));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_index_out_of_bounds));
        } catch (NumberFormatException e) {
            System.out.println(constructMessage(task_number_format));
        }
    }

    private String printAsString(List<Task> taskList) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            res.append(i)
                    .append(". ")
                    .append(taskList.get(i - 1));
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    private boolean canRun() {
        return running;
    }

    public String constructMessage(String message) {
        return dash + "\n" + message + "\n" + dash;
    }
}
