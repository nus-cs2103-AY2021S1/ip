import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File dataFile = new File(filePath);
            if (!dataFile.getParentFile().exists()) {
                if (dataFile.mkdirs()) {
                    new Ui("Data Directory is created...").printMessage();
                } else {
                    new Ui("Oops...the data directory cannot be created :(").printMessage();
                }
            } else {
                new Ui("Data directory located...").printMessage();
            }
            if (!dataFile.exists()) {
                if (dataFile.createNewFile()) {
                    new Ui("Data file is created...\nInitialization complete!\n").printMessage();
                } else {
                    new Ui("Oops...the data file cannot be created :(").printMessage();
                }
            } else {
                new Ui("Data file located...").printMessage();
                loadExistingData();
                new Ui("Initialization complete!").printMessage();
            }
        } catch (IOException e) {
            new Ui("An error occurred.").printMessage();
            e.printStackTrace();
        } catch (DukeException e) {
            new Ui("Initialization failed.\ne.getMessage()\n").printMessage();
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