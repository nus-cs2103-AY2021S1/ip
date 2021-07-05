package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;

import data.Deadline;
import data.Events;
import data.Task;
import data.TaskList;
import data.ToDo;



/**
 * Encapsulates a Storage class.
 * Saves the existing task list of Duke into the hard drive.
 */
public class Storage {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;

    }


    /**
     * Loads the task list from the hard drive.
     *
     * @throws IOException Exception when the file is not able to be created successfully.
     */
    private void initTaskList() throws IOException {

        try {
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            //process the line
            Task task;
            char taskType = line.charAt(1);
            if (taskType == 'T') {
                String description = line.substring(7);
                task = new ToDo(description);

            } else if (taskType == 'E') {
                String[] temp = line.substring(7).split(" \\(at: ");
                String description = temp[0];
                String[] dateTime = temp[1].substring(0, temp[1].length() - 1).split("-");
                LocalDate by = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                task = new Events(description, by);

            } else {
                String[] temp = line.substring(7).split(" \\(by: ");
                String description = temp[0];
                String[] dateTime = temp[1].substring(0, temp[1].length() - 1).split("-");
                LocalDate at = LocalDate.of(Integer.parseInt(dateTime[0]), Integer.parseInt(dateTime[1]),
                    Integer.parseInt(dateTime[2]));
                task = new Deadline(description, at);

            }

            if (line.charAt(4) == '\u2713') {
                task.doTask();
            }
            tasks.add(task);
        }
    }

    /**
     * Loads the task list from the file path.
     *
     * @return task list from the file path, in array list.
     */

    public ArrayList<Task> load() {
        try {
            initTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Saves the task list to the hard drive.
     *
     * @param tasks TaskList to be saved.
     */

    public void save(TaskList tasks) {
        try { //write the list to file
            FileWriter myWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                myWriter.write(tasks.get(i) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
