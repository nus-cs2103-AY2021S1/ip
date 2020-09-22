package data;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * data.Storage object handles saving and loading of data for list of Task objects.
 * Task objects are stored as String representations in specified .txt file.
 *
 * @author Hakiem Rasid
 */
public class Storage {

    private String filePath;

    /**
     * Constructor of data.Storage object.
     *
     * @param filePath Target .txt file for saving and loading of data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sends data of list of Task objects as String to be saved onto
     * target .txt file.
     * Format of each line in txt file: done/incomplete t/d/e description [time: time]
     *
     * @param taskList List of Task objects to be saved.
     */
    public void saveData(ArrayList<Task> taskList) {

        try {
            File file = new File(this.filePath);

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, false);

            for (Task task : taskList) {
                StringBuilder sb = new StringBuilder();
                if (task.isDone()) {
                    sb.append("done ");
                } else {
                    sb.append("incomplete ");
                }

                if (task instanceof ToDo) {
                    ToDo currentTask = (ToDo) task;
                    sb.append("t " + currentTask.getName());
                } else if (task instanceof Deadline) {
                    Deadline currentTask = (Deadline) task;
                    sb.append("d " + currentTask.getName() + " timeOfTask: " + currentTask.getDeadline());
                } else if (task instanceof Event) {
                    Event currentTask = (Event) task;
                    sb.append("e " + currentTask.getName() + " timeOfTask: " + currentTask.getTime());
                } else {
                    // do nothing
                }
                sb.append("\n");
                writer.write(sb.toString());
            } // end for loop
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of Task objects after loading data from .txt file.
     * Returns empty list if no data is found.
     *
     * @return List of Task objects if data is found and loaded. Empty list
     * if no data is found.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                String[] lineArray = currentLine.split(" ");
                boolean isDoneTask = lineArray[0].equals("done");
                String type = lineArray[1];
                String description;
                String time;
                Task newTask;

                // eg. done d this is a description timeOfTask: 12-12-2020 1455
                if (type.equals("t")) {
                    // case ToDo
                    for (int i = 2; i < lineArray.length; i++) {
                        sb.append(lineArray[i] + " ");
                    }
                    description = sb.toString().trim();
                    newTask = new ToDo(description);
                } else {
                    // case Deadline/Event
                    for (int i = 2; i < lineArray.length; i++) {
                        if (lineArray[i].equals("timeOfTask:")) {
                            break;
                        }
                        sb.append(lineArray[i] + " ");
                    }
                    description = sb.toString().trim();
                    time = currentLine.split("timeOfTask:")[1].trim();
                    newTask = (type.equals("d"))
                            ? new Deadline(description, time)
                            : new Event(description, time);
                }

                if (isDoneTask) {
                    newTask.completeTask();
                }
                list.add(newTask);
            } // end while loop

        } catch (FileNotFoundException e) {
            // returns empty ArrayList if savedata text file not found
            return new ArrayList<>();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }

}