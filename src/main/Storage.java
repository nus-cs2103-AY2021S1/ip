import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File dataFile = new File(filePath);
            if (!dataFile.getParentFile().exists()) {
                if (dataFile.mkdirs()) {
                    System.out.println("Data Directory is created...");
                } else {
                    System.out.println("Oops...the data directory cannot be created :(");
                }
            } else {
                System.out.println("Data directory located...");
            }
            if (!dataFile.exists()) {
                if (dataFile.createNewFile()) {
                    System.out.println("Data file is created...");
                    System.out.println("Initialization complete!");
                } else {
                    System.out.println("Oops...the data file cannot be created :(");
                }
            } else {
                System.out.println("Data file located...");
                loadExistingData();
                System.out.println("Initialization complete!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.print("Initialization failed. ");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadExistingData() throws IOException, DukeException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ArrayList<Task> tasks = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] taskInfo = line.split(" \\| ");
            if (taskInfo.length < 3) {
                throw new DukeException("Corrupted file: missing field.\n");
            } else if (!(taskInfo[1].equals("0") || taskInfo[1].equals("1"))) {
                throw new DukeException("Corrupted file: invalid done field.\n");
            } else {
                String type = taskInfo[0];
                boolean isDone = taskInfo[1].equals("1");
                String desc = taskInfo[2];

                Task task;
                switch (type) {
                    case ("T"):
                        task = new ToDo(desc);
                        break;
                    case ("D"):
                        String by = taskInfo[3];
                        task = new Deadline(desc, by);
                        break;
                    case ("E"):
                        String at = taskInfo[3];
                        task = new Event(desc, at);
                        break;
                    default:
                        throw new DukeException("Corrupted file: invalid task type.\n");
                }
                if (isDone) {
                    task.makeDone();
                }
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void updateHardDisk(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            for (Task t : tasks) {
                writer.write(t.toData() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}