package duke.storage;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

/** This class deals with loading tasks from the file and saving tasks in the file */
public class Storage {
    private String path;
    private BufferedWriter writer;

    /**
     * Constructs a Storage object with a path as the given path and a BufferedWriter object
     * that writes to the file located at the given path.
     * @param path The path of the storage file.
     */
    public Storage(String path) throws IOException {
        this.path = path;
        File file = new File(path);
        if (!Files.exists(Paths.get("./data"))) {
            new File("./data").mkdir();
        }
        file.createNewFile();
        this.writer = new BufferedWriter(new FileWriter(path, true));
    }

    /**
     * Loads the tasks from the file.
     * @return A List containing the saved tasks.
     * @throws DukeException An exception while loading tasks from the file.
     */
    public List<Task> load() throws IOException, DukeException {
        List<Task> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String task = br.readLine();

        while(task != null) {
            if (!task.equals("")) {
                String delimiter = " ~/~ ";
                String[] splits = task.split(delimiter);

                Task t = null;
                String taskType = splits[0];
                switch (taskType) {
                case "T":
                    String todo = splits[2];
                    t = new ToDo(todo);
                    break;
                case "D":
                    String description = splits[2];

                    String dateString = splits[3];
                    LocalDate date = LocalDate.parse(dateString);

                    if (splits.length == 4) {
                        t = new Deadline(description, date);
                    } else if (splits.length == 5) {
                        String timeString = splits[4];
                        LocalTime time = LocalTime.parse(timeString);

                        t = new Deadline(description, date, time);
                    }
                    break;
                case "E":
                    description = splits[2];

                    dateString = splits[3];
                    date = LocalDate.parse(dateString);

                    if (splits.length == 4) {
                        t = new Event(description, date);
                    } else if (splits.length >= 5) {
                        String startTimeString = splits[4];
                        LocalTime startTime = LocalTime.parse(startTimeString);

                        if (splits.length == 5) {
                            t = new Event(description, date, startTime);
                        } else if (splits.length == 6) {
                            String endTimeString = splits[5];
                            LocalTime endTime = LocalTime.parse(endTimeString);
                            t = new Event(description, date, startTime, endTime);
                        }
                    }
                    break;
                default:
                    throw new DukeException("Please check the hard disk for any errors");
                }

                String isDone = splits[1];
                if (isDone.equals("1")) {
                    t.markAsDone();
                }

                list.add(t);
            }

            task = br.readLine();
        }

        return list;
    }

    /**
     * Saves task to the file.
     * @param task The task to be saved.
     */
    public void add(Task task) throws IOException {
        writer.write(task.txtFileFormat());
        writer.newLine();
        writer.flush();
    }

    /**
     * Refreshes the storage to reflect all tasks in the TaskList.
     * @param taskList The TaskList that the storage will save tasks from.
     */
    public void refresh(TaskList taskList) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(this.path));
        for (Task t : taskList.getList()) {
            writer.write(t.txtFileFormat());
            writer.newLine();
        }
        this.writer.flush();
        this.writer = new BufferedWriter(new FileWriter(this.path, true));
    }
}
