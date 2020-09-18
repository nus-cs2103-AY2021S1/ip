package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {

    private String pathName;
    private String fileName;

    /**
     * Returns a storage class which takes in two String arguments of pathName and fileName (data and duke.txt)
     * It is a constructor of the storage class which will create duke.txt if it does not exist;
     * Retrieves tasks from the duke.txt; And write new tasks into the duke.txt when user exits
     *
     * @param pathName The path of the file
     * @param fileName The name of the file
     * @return A Storage
     */
    public Storage(String pathName, String fileName) {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    /**
     * Returns a TaskList that stores tasks retrieved from the data/duke.txt if the file exits
     *
     * @return a TaskList with tasks stored in the txt file.
     */
    public TaskList load() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            File file = new File(this.pathName);
            File dukeSave = new File(this.pathName + this.fileName);
            if (!file.exists()) {
                file.mkdirs();
                dukeSave.createNewFile();
            } else if (dukeSave.exists()) {
                this.addPreviousTask(file, tasks);
            } else {
                dukeSave.createNewFile();
            }
        } catch (FileNotFoundException e) {
            e.toString();
        } catch (IOException e) {
            e.toString();
        }
        return tasks;
    }

    /**
     * Decodes the tasks saved in the file and store them into a TaskList
     * @param file The file to read
     * @param tasks The TaskList which store the tasks
     *
     * @throws FileNotFoundException Exception occurs when the file is not found
     */
    public void addPreviousTask(File file, TaskList tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String previouTaskString = sc.nextLine();
            String[] words = previouTaskString.split(" @ ", 0);
            Task previouTask = null;
            if (words[0].equals("D")) {
                previouTask = new Deadline(words[2], words[3]);
                tasks.add(previouTask);
            } else if (words[0].equals("T")) {
                previouTask = new ToDo(words[2]);
                tasks.add(previouTask);
            } else if (words[0].equals("E")) {
                previouTask = new Event(words[2], words[3]);
                tasks.add(previouTask);
            }
            if (words[1].equals("1")) {
                previouTask.setIsDone();
            }
        }
    }

    /**
     * Stores newly added tasks and previous tasks from the TaskList in the duke.txt file
     *
     * @param tasks TaskList with newly added tasks and previous tasks
     * @throws IOException exception occurs when the file is not found
     */
    public void storeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        List<String> fileString = tasks.convertToFileFormat();
        for (int k = 0; k < fileString.size(); k++) {
            fw.write(fileString.get(k));
        }
        fw.close();
    }


}
