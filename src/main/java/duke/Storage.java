package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import duke.merchandise.Merchandise;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The Storage class will deal with loading tasks from the file
 * and saving tasks in the file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for a Storage object.
     *
     * @param filePath A String representation of the filepath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file which is stored in the hard disk and
     * retrieves the information from the file.
     *
     * @return An arraylist which contains the Tasks stored in the file.
     * @throws DukeException if an IO error occurred and createNewFile() throws IOException.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            File file = new File(filePath + "/tasks.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();
            return readTasksFile(file);
        } catch (IOException error) {
            throw new DukeException("IOException");
        }
    }

    public ArrayList<Task> readTasksFile(File file) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] taskDetails = task.split(" \\| ");
            String taskType = taskDetails[0];
            String isTaskDone = taskDetails[1];
            String taskName = taskDetails[2];
            String taskTime;
            if (taskType.equals("T")) {
                tasks.add(new ToDo(taskName));
            } else if (taskType.equals("D")) {
                taskTime = taskDetails[3];
                tasks.add(new Deadline(taskName, LocalDate.parse(taskTime)));
            } else if (taskType.equals("E")) {
                taskTime = taskDetails[3];
                tasks.add(new Event(taskName, LocalDate.parse(taskTime)));
            } else {
                taskType = "unknown";
            }
            if (isTaskDone.equals("1")) {
                tasks.get(tasks.size() - 1).setAsDone();
            }
            assert taskType != "unknown" : "Task type was not saved properly";
            assert isTaskDone.equals("0") || isTaskDone.equals("1")
                    : "IsDone was not saved properly";
            if (isTaskDone.equals("1")) {
                tasks.get(tasks.size() - 1).setAsDone();
            }
        }
        sc.close();
        return tasks;
    }

    /**
     * Updates the data file to store all the Tasks in the list.
     *
     * @param tasks An arraylist which contains information for all the Tasks.
     * @throws IOException if the file in filePath does not exist or cannot be opened.
     */
    public void updateTasksFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath + "/tasks.txt");
        PrintWriter printLine = new PrintWriter(writer);
        for (Task task : tasks) {
            String taskDetails;
            String isTaskDone = task.getIsDone() ? "1" : "0";
            if (task instanceof ToDo) {
                taskDetails = "T | " + isTaskDone + " | " + task.getName();
            } else if (task instanceof Deadline) {
                taskDetails = "D | " + isTaskDone + " | " + task.getName() + " | "
                        + ((Deadline) task).getBy().toString();
            } else if (task instanceof Event) {
                taskDetails = "E | " + isTaskDone + " | " + task.getName() + " | "
                        + ((Event) task).getAt().toString();
            } else {
                taskDetails = "no details";
            }
            assert !taskDetails.equals("no details") : "Unable to update data file";
            printLine.printf("%s\n", taskDetails);
        }
        printLine.close();
    }

    public ArrayList<Merchandise> loadMerchandises() throws DukeException {
        try {
            File file = new File(filePath + "/merchandises.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();
            return readMerchandisesFile(file);
        } catch (IOException error) {
            throw new DukeException("IOException");
        }
    }

    public ArrayList<Merchandise> readMerchandisesFile(File file) throws FileNotFoundException {
        ArrayList<Merchandise> merchandises = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String itemName = sc.nextLine();
            merchandises.add(new Merchandise(itemName));
        }
        sc.close();
        return merchandises;
    }

    public void updateMerchandisesFile(ArrayList<Merchandise> merchandises) throws IOException {
        FileWriter writer = new FileWriter(filePath + "/merchandises.txt");
        PrintWriter printLine = new PrintWriter(writer);
        for (Merchandise merchandise : merchandises) {
            String merchandiseDetails = merchandise.getItemName();
            printLine.printf("%s\n", merchandiseDetails);
        }
        printLine.close();
    }
}
