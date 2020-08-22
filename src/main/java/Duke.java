import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    static final String SAVE_FILE = "save.txt";
    static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat SAVE_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

    public static void main(String[] args) {
        System.out.println("Famed, of course, for my unique red skin and unparalleled skills as a general of the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I assure you: I remain undaunted - and as determined as ever to claim my rightful throne!");

        Scanner sc = new Scanner(System.in);
        List<Task> taskList = loadFile();

        String input = sc.nextLine();
        while (true) {
            Scanner inputSc = new Scanner(input);
            String task = inputSc.next();
            switch (task) {
            case "bye":
                System.out.println("*You take your leave.*");
                saveFile(taskList);
                return;
            case "list":
                System.out.println("Here's the extent of our list so far:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                break;
            case "done":
                try {
                    int taskIndex = inputSc.nextInt();
                    Task markedTask = taskList.get(taskIndex - 1); // shown list is base 1, implemented list is base 0
                    markedTask.markDone();
                    System.out.println("Right. This task is now marked as done:" + markedTask);
                } catch (IndexOutOfBoundsException | NumberFormatException | InputMismatchException e) {
                    System.out.println("Hmm? Please mention \"done\" followed by the number of the task we're marking as done.");
                }
                break;
            case "delete":
                Task deletedTask = taskList.remove(inputSc.nextInt() - 1); // shown list is base 1, implemented list is base 0
                System.out.println("Begone! This task is now removed: " + deletedTask);
                break;
            case "todo":
                try {
                    String name = inputSc.nextLine();
                    Todo newTodo = new Todo(name);
                    taskList.add(newTodo);
                    System.out.println("Fine. I added the following to the list: " + newTodo);
                } catch (DukeException | NoSuchElementException e) {
                    System.out.println(e);
                }
                break;
            case "deadline":
                try {
                    String[] nameAndDeadline = inputSc.nextLine().split(" /by ");
                    Deadline newDeadline = new Deadline(nameAndDeadline[0], nameAndDeadline[1]);
                    taskList.add(newDeadline);
                    System.out.println("Fine. I added the following to the list: " + newDeadline);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "event":
                try {
                    String[] nameAndEvent = inputSc.nextLine().split(" /at ");
                    Event newEvent = new Event(nameAndEvent[0], nameAndEvent[1]);
                    taskList.add(newEvent);
                    System.out.println("Fine. I added the following to the list: " + newEvent);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("What's that? Please mention one of \"list\", \"done\", \"todo\", \"deadline\", \"event\", or \"bye\".");

            }
            /*
            if (input.equals("list")) {
                System.out.println("Here's the extent of our list so far:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (input.startsWith("done")) {
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task markedTask = taskList.get(taskIndex - 1); // shown list is base 1, implemented list is base 0
                    markedTask.markDone();
                    System.out.println("Right. This task is now marked as done:" + markedTask);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Hmm? Please mention \"done\" followed by the number of the task we're marking as done.");
                }
            } else if (input.startsWith("todo")) {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Fine. I added the following to the list: " + newTask);
            }*/

            input = sc.nextLine();
        }
    }

    private static List<Task> loadFile() {
        List<Task> taskList = new ArrayList<>();

        try {
            File save = new File(SAVE_FILE);
            Scanner saveSc = new Scanner(save);
            while (saveSc.hasNextLine()) {
                String taskString = saveSc.nextLine();
                Task loadedTask = stringToTask(taskString);
                taskList.add(loadedTask);
            }
            saveSc.close();
        } catch (FileNotFoundException e) {
            System.out.println("save.txt not found. Creating empty list.");
        } catch (DukeException e) {
            System.out.println(e);
        }

        return taskList;
    }

    private static Task stringToTask(String taskString) throws DukeException{
        Task newTask;

        Scanner taskSc = new Scanner(taskString);
        String taskType = taskSc.next();
        String taskCompletion = taskSc.next();
        String taskDesc = taskSc.nextLine().trim();

        switch (taskType) {
        case "[TODO]":
            newTask = new Todo(taskDesc);
            break;
        case "[DEADLINE]":
            try {
                String[] nameAndDeadline = taskDesc.split(" \\| by: ");
                Date deadlineDate = SAVE_DATE_FORMAT.parse(nameAndDeadline[1]);
                newTask = new Deadline(nameAndDeadline[0], INPUT_DATE_FORMAT.format(deadlineDate));
            } catch (ParseException e) {
                throw new DukeException("Loading date error: " + e);
            }
            break;
        case "[EVENT]":
            try {
                String[] nameAndEvent = taskDesc.split(" \\| at: ");
                Date eventDate = SAVE_DATE_FORMAT.parse(nameAndEvent[1]);
                newTask = new Event(nameAndEvent[0], INPUT_DATE_FORMAT.format(eventDate));
            } catch (ParseException e) {
                throw new DukeException("Loading date error: " + e);
            }
            break;
        default:
            throw new DukeException("Loading task failed: unrecognized task type: " + taskType);
        }

        if (taskCompletion.equals("[✓]")) {
            newTask.markDone();
        }

        return newTask;
    }

    private static void saveFile(List<Task> taskList) {
        try {
            FileWriter save = new FileWriter(SAVE_FILE);
            for (Task task : taskList) {
                save.write(task.toString() + "\n");
            }
            save.close();
        } catch (IOException e) {
            System.out.println("Saving failed!");
        }
    }
}
