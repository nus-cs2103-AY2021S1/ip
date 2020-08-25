package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the Duke Project text file exists. If it does, reads each line of the
     * file and processes it into a TaskList. If not, creates the text file in the
     * specified directory.
     *
     * @return Returns a TaskList based on the user's duke project text file.
     * @throws DukeException  If the file was not created correctly/ does not exist.
     */
    public TaskList load() throws DukeException {
        File f = new File(this.filePath); // create a File for the given file path
        if (!f.exists()) {
            final File parentDir = new File("data");
            parentDir.mkdir();
            final String hash = "tasks";
            final String fileName = hash + ".txt";
            final File file = new File(parentDir, fileName);
            try {
                file.createNewFile();
                System.out.println("Created path data/tasks.txt");
            } catch (IOException e) {
                System.out.println("Could not create file.");
            }
        }
        Scanner s;
        try {
            s = new Scanner(f); // create a Scanner using the File as the source
        } catch (Exception e) {
            throw new DukeException("File not found.");
        }
        ArrayList<Task> loadedTasks = new ArrayList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            switch (task.charAt(1)) {
            case ('T'):
                if (task.charAt(4) == '✓') {
                    Task newTask = new ToDo(task.substring(7));
                    newTask.setDone();
                    loadedTasks.add(newTask);
                } else if (task.charAt(4) == '✘') {
                    Task newTask = new ToDo(task.substring(7));
                    loadedTasks.add(newTask);
                }
                break;
            case ('D'):
                if (task.charAt(4) == '✓') {
                    int indexOfDeadline = task.indexOf("by:");
                    String date = task.substring(indexOfDeadline + 4, task.length() - 1);
                    String description = task.substring(7, indexOfDeadline - 2);
                    Task newTask = new Deadline(description, date);
                    newTask.setDone();
                    loadedTasks.add(newTask);
                } else if (task.charAt(4) == '✘') {
                        int indexOfDeadline = task.indexOf("by:");
                        String date = task.substring(indexOfDeadline + 4, task.length() - 1);
                        String description = task.substring(7, indexOfDeadline - 2);
                        Task newTask = new Deadline(description, date);
                        loadedTasks.add(newTask);
                    }
                    break;
            case ('E'):
                if (task.charAt(4) == '✓') {
                    int indexOfEvent = task.indexOf("at:");
                    String date = task.substring(indexOfEvent + 4, task.length() - 1);
                    String description = task.substring(7, indexOfEvent - 2);
                    Task newTask = new Event(description, date);
                    newTask.setDone();
                    loadedTasks.add(newTask);
                } else if (task.charAt(4) == '✘') {
                    int indexOfEvent = task.indexOf("at:");
                    String date = task.substring(indexOfEvent + 4, task.length() - 1);
                    String description = task.substring(7, indexOfEvent - 2);
                    Task newTask = new Event(description, date);
                    loadedTasks.add(newTask);
                }
                break;
            }
        }
        return new TaskList(loadedTasks);
    }

    /**
     * Writes the input text onto the specified file path.
     *
     * @param textToAdd Text to be written onto the specified file.
     * @throws IOException  If the specified file does not exist.
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Writes the user's current TaskList into the project duke text file in a readable manner.
     *
     * @param tasks The user's current TaskList.
     */
    public static void store(ArrayList<Task> tasks) {
        try {
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks) {
                textToAdd.append(task.toString()).append(System.lineSeparator());
            }
            writeToFile(textToAdd.toString());
        } catch (IOException e) {
            System.out.println("Something Went wrong: " + e.getMessage());
        }
    }
}
