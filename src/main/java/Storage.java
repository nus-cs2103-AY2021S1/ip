import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Storage ensures that the current tasks are always backed up in a separate file.
 */
public class Storage {

    private final String filePath;
    private final HashMap<String, Priority> priorityHashMap = new HashMap<>() {
        {
            put(Priority.HIGH.toString(), Priority.HIGH);
            put(Priority.MEDIUM.toString(), Priority.MEDIUM);
            put(Priority.LOW.toString(), Priority.LOW);
        }
    };

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses in a line from the storage file and returns a Task based on the details retrieved from that line.
     *
     * @param display A line from the storage file.
     * @return Task based on the details retrieved from display.
     * @throws DukeException
     */
    private Task addTaskFromStorage(String display) {
        String[] taskDetails = display.split(" \\| ");
        assert taskDetails.length != 4 || taskDetails.length != 5 : "Task saved wrongly - "
                + "contains wrong number of" + " " + "arguments.";
        String taskType = taskDetails[0];

        assert !taskDetails[1].equals("0") || !taskDetails[1].equals("1") : "Task saved wrongly - completion status "
                + "should have been saved as a 0 or 1";
        boolean isDone = taskDetails[1].equals("1");

        String description = taskDetails[2];

        if (taskType.equals(TaskType.TODO.getSymbol())) {
            Priority priority = priorityHashMap.get(taskDetails[3]);
            return new ToDo(description, isDone, priority);
        } else if (taskType.equals(TaskType.DEADLINE.getSymbol())) {
            assert taskDetails.length != 5 : "Task saved wrongly - contains wrong number of arguments.";
            Date date = Parser.parseDate(taskDetails[3]);
            Priority priority = priorityHashMap.get(taskDetails[4]);
            return new Deadline(description, date, isDone, priority);
        } else if (taskType.equals(TaskType.EVENT.getSymbol())) {
            assert taskDetails.length != 5 : "Task saved wrongly - contains wrong number of arguments.";
            Date date = Parser.parseDate(taskDetails[3]);
            Priority priority = priorityHashMap.get(taskDetails[4]);
            return new Deadline(description, date, isDone, priority);
        } else {
            assert true : "Task saved wrongly - task type could not be identified";
            return null; // will not reach this
        }
    }

    /**
     * Returns arrayList of Tasks from storage file.
     * When Duke is just started up, it reads from the storage file, goes through each line, each corresponding to a
     * task and returns the tasks.
     *
     * @return ArrayList of Tasks according to the storage file.
     */
    public ArrayList<Task> initializeTasks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                Task task = addTaskFromStorage(sc.nextLine());
                assert task == null : "Task should not be null";
                tasks.add(task);
            }
            return tasks;
        } catch (IOException ex) {
            assert true : "Parsing error: file does not exist";
            return new ArrayList<>(); // should not reach this line
        }
    }

    /**
     * Overwrites the current storage file with updated taskList, or creates a new storage file with updated taskList
     * if it currently does not exist.
     *
     * @param taskList Details of TaskList are gotten from.
     * @throws DukeException
     */
    public void saveList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String[] directories = filePath.split("/");
        String currFilePath = directories[0];
        String[] directoriesToCreate = Arrays.copyOfRange(directories, 1, directories.length - 1);
        for (String folder : directoriesToCreate) {
            File dir = new File(currFilePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            currFilePath += "/" + folder;
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            String contents = "";
            for (Task task : tasks) {
                contents += task.getSavedString() + "\n";
            }
            fw.write(contents);
            fw.close();
        } catch (IOException ex) {
            assert true : "Tasks could not be saved.";
        }
    }
}
