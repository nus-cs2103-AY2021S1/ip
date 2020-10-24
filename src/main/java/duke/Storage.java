package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is responsible for loading saving data(tasks).
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns list of tasks loaded from saved data stored as text file.
     *
     * @param duke App which is currently used.
     * @return ArrayList containing tasks from text file.
     */
    public ArrayList<Task> load(Duke duke) throws IOException {
        // read file
        try {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String userInput = reader.nextLine();
                duke.handleUserInput(userInput, true);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // create folder if doesn't exist
            File path = new File("data/");
            if (!path.isDirectory()) {
                path.mkdir();

            }

            // create file if doesn't exist
            File file = new File("data/duke.txt");
            file.createNewFile();

            // Load again
            load(duke);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
        }
        ArrayList<Task> taskList = duke.tasks.taskList;
        return taskList;
    }

    /**
     * Saves tasks to text file in specific folder.
     *
     * @param taskList Contains list of tasks which will be saved.
     */
    static void save(ArrayList<Task> taskList) throws IOException {
        File file = new File("data/duke.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        int index = 1;
        for (Task task: taskList) {
            // convert task into instruction(user input)
            String taskInst = "";
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                taskInst = String.format("todo %s\n", todo.description);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                taskInst = String.format("event %s /at %s\n", event.description, event.eventTime);
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                taskInst = String.format("deadline %s /by %s\n", task.description, deadline.deadline);
            }

            //write instruction to text file
            assert taskInst != "" : "task instuction should not be empty";
            fileWriter.write(taskInst);

            // add done instruction if task is done
            if (task.isDone) {
                fileWriter.write(String.format("done %s\n", index));
            }

            // add set priority instruction if task has priority level
            if (task.getPriorityLevel() != null) {
                fileWriter.write(String.format("priority %s to task %s\n", task.getPriorityLevel(), index));
            }

            index += 1;
        }
        fileWriter.close();
    }
}
