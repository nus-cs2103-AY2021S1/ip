package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            ArrayList<Task> list = new ArrayList<>();
            boolean hasFolder = file.getParentFile().mkdirs();
            boolean hasFile = file.createNewFile();
            if (!hasFile && !hasFolder) {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String task = sc.nextLine();
                    String[] taskDetails = task.split(" \\| ");
                    String taskType = taskDetails[0];
                    String isTaskDone = taskDetails[1];
                    String taskName = taskDetails[2];
                    String taskTime;
                    if (taskType.equals("T")) {
                        list.add(new ToDo(taskName));
                    } else if (taskType.equals("D")) {
                        taskTime = taskDetails[3];
                        list.add(new Deadline(taskName, LocalDate.parse(taskTime)));
                    } else if (taskType.equals("E")) {
                        taskTime = taskDetails[3];
                        list.add(new Event(taskName, LocalDate.parse(taskTime)));
                    } else {
                        taskType = "unknown";
                    }
                    assert taskType != "unknown" : "Task type was not saved properly";
                    assert isTaskDone.equals("0") || isTaskDone.equals("1")
                            : "IsDone was not saved properly";
                    if (isTaskDone.equals("1")) {
                        list.get(list.size() - 1).done();
                    }
                }
                sc.close();
            }
            return list;
        } catch (IOException error) {
            throw new DukeException("IOException");
        }
    }

    /**
     * Updates the data file to store all the Tasks in the list.
     *
     * @param list An arraylist which contains information for all the Tasks.
     * @throws IOException if the file in filePath does not exist or cannot be opened.
     */
    public void updateDataFile(ArrayList<Task> list) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        PrintWriter printLine = new PrintWriter(writer);
        for (Task task : list) {
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
}
