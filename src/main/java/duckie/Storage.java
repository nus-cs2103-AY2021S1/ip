package duckie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duckie.exception.DuckieException;
import duckie.exception.DuckieFileErrorException;
import duckie.task.Deadline;
import duckie.task.Event;
import duckie.task.Task;
import duckie.task.Todo;


/**
 * Deals with the stored duckie file in the HardDrive
 */
public class Storage {
    private String filePath;

    /**
     * Instantiates a Storage object
     *
     * @param filePath Path of duckie.txt.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the duckie file from the harddrive
     * Creates the file and directory if cannot be found
     *
     * @return File containing the saved tasks.
     * @throws DuckieException Throw FileError Exception.
     */
    public File openFile() throws DuckieException {
        try {
            File duckieFile = new File(this.filePath);
            File duckieDir = new File(duckieFile.getParent());

            if (!duckieDir.exists()) {
                duckieDir.mkdirs();
            }

            if (!duckieFile.exists()) {
                duckieFile.createNewFile();
            }
            return duckieFile;
        } catch (IOException e) {
            throw new DuckieFileErrorException();
        }
    }

    private Task createTodo(String isDone, String description) {
        Task toDo = new Todo(description);
        if (isDone.equals("1")) {
            toDo.markDone();
        }
        return toDo;
    }

    private Task createDeadline(String isDone, String description, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        LocalDate d1 = LocalDate.parse(date, formatter);
        Deadline taskD = new Deadline(description, d1);
        if (isDone.equals("1")) {
            taskD.markDone();
        }
        return taskD;
    }

    private Task createEvent(String isDone, String description, String dateTime) {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
        LocalDateTime d2 = LocalDateTime.parse(dateTime, formatter2);
        Event taskE = new Event(description, d2);
        if (isDone.equals("1")) {
            taskE.markDone();
        }
        return taskE;
    }

    /**
     * Load the duckie file and generate the ArrayList containing the tasks
     *
     * @return ArrayList containing all the saved tasks.
     * @throws DuckieException Throw File Error exception.
     */
    public ArrayList<Task> load() throws DuckieException {
        File duckieFile = openFile();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(duckieFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskBreakdown = task.split("\\|");

                String type = taskBreakdown[0].strip();
                assert type == "T" || type == "D" || type == "E" : "Task type is wrong.";

                String isDone = taskBreakdown[1].strip();
                assert isDone == "1" || isDone == "0" : "There should not be other numbers.";

                String description = taskBreakdown[2].strip();
                switch (type) {
                    case "T":
                        Task taskToDo = createTodo(isDone, description);
                        tasks.add(taskToDo);
                        break;
                    case "D":
                        String date = taskBreakdown[3].strip();
                        Task taskD = createDeadline(isDone, description, date);
                        tasks.add(taskD);
                        break;
                    case "E":
                        String dateTime = taskBreakdown[3].strip();
                        Task taskE = createEvent(isDone, description, dateTime);
                        tasks.add(taskE);
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckieException("Quack! Duckie cannot find your File!");
        }

        return tasks;
    }

    /**
     * Update the current tasks in the TaskList to the duckie file
     *
     * @param tasks List containing all the current tasks
     * @throws DuckieException Throw File Error Exception.
     */
    public void saveToFile(ArrayList<Task> tasks) throws DuckieException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String toWrite = "";
            for (Task t1 : tasks) {
                toWrite += (t1.getType() + (t1.isCompleted() ? " | 1 | " : " | 0 | ")
                        + t1.getDescription())
                        + (t1.getDate() != null ? "| " + t1.getDate() : "")
                        + System.lineSeparator();
            }
            fw.write(toWrite);
            fw.close();
        } catch (IOException e) {
            throw new DuckieFileErrorException();
        }
    }
}
