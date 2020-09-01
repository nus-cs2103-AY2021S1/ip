package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String dirPath = "data";
    private final String txtPath = "data/duke.txt";
    private String path;

    /**
     * Storage Class constructor. Create a new directory if there isn't one at the given path.
     *
     * @param path give the path of the save data
     */
    public Storage() {
        this.path = txtPath;
        try {
            createFolder();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Method that creates a new folder at the root directory if there isn't one
     */
    private void createFolder() throws IOException {
        File dirFolder = new File(dirPath);
        File txt = new File(txtPath);
        if (!dirFolder.exists()) {
            dirFolder.mkdir();
        }
        if (!txt.exists()) {
            txt.createNewFile();
        }
    }

    /**
     * Method that load saved data (past records) from the directory
     *
     * @return a List of Task objects
     */
    public List<Task> loadSavedData() {
        List<Task> list = new ArrayList<>();

        try {
            File file = new File(txtPath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] splited = line.split(">");
                Task currTask = null;
                if (splited[0].equals("T")) {
                    currTask = new Todo(splited[2]);
                } else if (splited[0].equals("E")) {
                    currTask = new Event(splited[2], splited[3]);
                } else if (splited[0].equals("D")) {
                    currTask = new Deadline(splited[2], splited[3]);
                }
                if (splited[1].equals("1")) {
                    currTask.markAsDone();
                }
                list.add(currTask);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return list;
    }

    /**
     * Method that add Task data to the save data txt file
     *
     * @param task task in a String format
     */
    public void addTask(String task) throws IOException {
        File file = new File(txtPath);
        FileWriter writer = new FileWriter(file, true);
        writer.write(task);
        writer.write('\n');
        writer.flush();
        writer.close();
    }

    /**
     * Method that mark a specific task as complete in the saved data
     *
     * @param number index of the Task to be completed
     */
    public void completeTask(int number) throws IOException {
        File file = new File(txtPath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int lineNo = 0;
        String finalLine = "";
        while ((line = br.readLine()) != null) {
            lineNo += 1;
            if (lineNo == number) {
                StringBuilder updated = new StringBuilder(line);
                updated.setCharAt(2, '1');
                finalLine += updated + "\n";
            } else {
                finalLine += line + "\n";
            }
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(finalLine);
        writer.flush();
        writer.close();
    }

    /**
     * Method that delete a specific task from the saved data
     *
     * @param number index of the Task to be deleted
     */
    public void deleteTask(int number) throws IOException {
        File file = new File(txtPath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int lineNo = 0;
        String finalLine = "";
        while ((line = br.readLine()) != null) {
            lineNo += 1;
            if (lineNo == number) {
                continue;
            } else {
                finalLine += line + "\n";
            }
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(finalLine);
        writer.flush();
        writer.close();
    }
}
