package duke;

import java.util.ArrayList;
import java.nio.file.Path;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

/**
 * Represents a Storage that stores tasks.
 */
public class Storage {
    private static int globalIndex = 1;
    private int DONE_SYMBOL = 1;
    private final String tempFilePath = "storage" + File.separator + "temp.txt";
    private final String oldFilePath = "storage" + File.separator + "data.txt";

    /**
     * Creates an instance of a Storage.
     * 
     * @param filePath Location where the Storage is initialised.
     */
    public Storage(Path filePath) {
        try {
            if (!java.nio.file.Files.exists(filePath)) { 
                File newDir = new File("storage" + File.separator + "data.txt");
                newDir.getParentFile().mkdirs();
                newDir.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Copies stored tasks into a list of tasks.
     * 
     * @return ArrayList of stored tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> storedTasks = new ArrayList<>();
        try {
            String filePath = "storage" + File.separator + "data.txt";
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String task[] = currentLine.split("\\|");
                if (task.length != 0 && !task[0].equals("")) {
                    switch(task[1]) {
                    case "T":
                        ToDo todo = new ToDo(task[3]);
                        if (Integer.parseInt(task[2]) == 1) todo.markAsDone();
                        storedTasks.add(todo);
                        globalIndex++;
                        break;
                    case "D":
                        Deadline deadline = new Deadline(task[3]);
                        if (Integer.parseInt(task[2]) == 1) deadline.markAsDone();
                        storedTasks.add(deadline);
                        globalIndex++;
                        break;
                    case "E":
                        Event event = new Event(task[3]);
                        if (Integer.parseInt(task[2]) == 1) event.markAsDone();
                        storedTasks.add(event);
                        globalIndex++;
                        break;
                    default:
                        throw new DukeException("Invalid item found");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return storedTasks;
    }

    /**
     * Writes a new task to file containing stored tasks.
     */
    public static void writeToFile(Task t, String[] input) {
        assert (t != null) : "Task is null";
        String type = "";
        int done = 0;
        try {
            if (t != null) {
                if (t instanceof ToDo) {
                    type = "T";
                } else if (t instanceof Deadline) {
                    type = "D";
                } else if (t instanceof Event) {
                    type = "E";
                }

                if (t.isDone()) {
                    done = 1;
                }
                FileWriter fw = new FileWriter("storage" + File.separator + "data.txt", true);
                fw.write(globalIndex + "|" + type + "|" + done + "|" + input[1] + "\n");
                globalIndex++;
                fw.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Removes a specific task from file containing stored tasks.
     * 
     * @param taskIndex Number corresponding to the specific task to be removed.
     */
    public void removeFromFile(int taskIndex) {
        assert !(taskIndex <= 0) : "Task index is invalid";
        try {
            int index = 1;
            File tempFile = new File(tempFilePath);
            FileReader fr = new FileReader(oldFilePath);
            BufferedReader br = new BufferedReader(fr);
            
            String currentLine;
            FileWriter fw = new FileWriter(tempFile, true);
            
            while ((currentLine = br.readLine()) != null) {
                String[] task = currentLine.split("\\|");
                if (Integer.parseInt(task[0]) != taskIndex) {
                    fw.write(index + "|" + task[1] + "|" + task[2] + "|" + task[3] + "\n");
                    index++;
                }
            }
            globalIndex = index;
            fw.close();
            fr.close();
            br.close();
            Files.copy(Paths.get(tempFilePath), Paths.get(oldFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
            
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Modifies a specific task in file containing stored tasks.
     * 
     * @param taskIndex Number corresponding to the specific task to be modified.
     */
    public void overwriteInFile(int taskIndex) {
        assert !(taskIndex <= 0) : "Task index is invalid";
        try {
            File tempFile = new File(tempFilePath);
            FileReader fr = new FileReader(oldFilePath);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            FileWriter fw = new FileWriter(tempFile, true);

            while ((currentLine = br.readLine()) != null) {
                String[] task = currentLine.split("\\|");
                if (Integer.parseInt(task[0]) != taskIndex) {
                    fw.write(currentLine + "\n");
                } else {
                    fw.write(taskIndex + "|" + task[1] + "|" + DONE_SYMBOL + "|" + task[3] + "\n");
                }
            }
            fw.close();
            fr.close();
            br.close();
            Files.copy(Paths.get(tempFilePath), Paths.get(oldFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
            
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
