package sparrow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store the task list.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns list of tasks loaded from hard disk.
     * If no file found, returns an empty list.
     * @return Task list.
     */
    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        // check if directory and file exist
        File f = new File("data/sparrow.Sparrow.txt");
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String task = sc.nextLine();
                    tasks.add(stringToTask(task));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            File g = new File("data");
            if (!g.exists()) {
                g.mkdirs();
            }
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    /**
     * Converts user input into a Task.
     * @param input user input to be converted.
     * @return Task object
     */
    public Task stringToTask(String input) {
        Task task = null;
        String[] inputArr = input.split("\\s+\\|\\s+", 4);
        boolean isTaskDone = Integer.parseInt(inputArr[1]) == 1;
        switch (inputArr[0]) {
        case "T":
            Todo todo = new Todo(inputArr[2]);
            if (isTaskDone) {
                todo.markAsDone();
            }
            task = todo;
            break;
        case "D":
            LocalDate dueDate = Sparrow.stringToDate(inputArr[3]);
            Deadline deadline = new Deadline(inputArr[2], dueDate);
            if (isTaskDone) {
                deadline.markAsDone();
            }
            task = deadline;
            break;
        case "E":
            LocalDate date = Sparrow.stringToDate(inputArr[3]);
            Event event = new Event(inputArr[2], date);
            if (isTaskDone) {
                event.markAsDone();
            }
            task = event;
            break;
        default:
            System.out.println("No matching task found, shouldn't end up here");
        }

        if (task == null) {
            System.out.println("No task created");
        }
        return task;
    }

    /**
     * Converts a Task into a String for storage.
     * @param task Task to be converted.
     * @return String representation of Task.
     */
    public String taskToString(Task task) {
        StringBuilder sb = new StringBuilder(task.getDescription());
        if (task.getIsDone()) {
            sb.insert(0, "1 | ");
        } else {
            sb.insert(0, "0 | ");
        }

        if (task instanceof Todo) {
            sb.insert(0,"T | ");
        } else if (task instanceof Deadline) {
            sb.insert(0,"D | ");
            sb.append(" | ").append(((Deadline) task).getDueDate());
        } else if (task instanceof  Event) {
            sb.insert(0,"E | ");
            sb.append(" | ").append(((Event) task).getDate());
        }

        sb.append("\n");

        return sb.toString();
    }

    /**
     * Saves specified task list to the hard disk.
     * @param taskList Task list to be saved.
     */
    public void saveTaskList(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter("data/sparrow.Sparrow.txt");
            for (Task task : taskList) {
                fw.append(taskToString(task));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
