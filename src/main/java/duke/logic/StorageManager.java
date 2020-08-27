package duke.logic;

import duke.CommonString;
import duke.task.DeadlineTask;
import duke.task.DukeTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {

    // FILE AND DATA STORAGE
    private static final String FILE_DATA_SEPARATOR = "|";
    private final String filePath;

    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    public StorageManager() {
        this.filePath = CommonString.DUKE_FILE_PATH.toString();
    }


    public ArrayList<DukeTask> loadData() throws FileNotFoundException {
        ArrayList<DukeTask> dataList = new ArrayList<>();

        File dataFile = new File(filePath);
        // load parent files
        File directory = new File(dataFile.getParentFile().getAbsolutePath());
        directory.mkdirs();

        // generate data file
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Problem with creating data file\n" + e.getMessage());
        }

        // read from data file
        if (dataFile.exists()) {
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNextLine()) {
                // regenerate the DukeTasks
                String savedTask = fileScanner.nextLine();
                String[] taskData = savedTask.split("\\|");
                DukeTask task;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonString.DUKE_DATETIME_FORMAT.toString());
                switch (taskData[0]) {
                    case "T":
                        task = new TodoTask(taskData[2]);
                        break;
                    case "E":
                        task = new EventTask(taskData[2], LocalDateTime.parse(taskData[3], formatter));
                        break;
                    default: // "D"
                        task = new DeadlineTask(taskData[2], LocalDateTime.parse(taskData[3], formatter));

                }
                if (taskData[1].equals("1")) {
                    task.markAsDone();
                }
                dataList.add(task);
            }
        }

        return dataList;
    }

    // SAVING DATA FOR OUTPUT
    // FORMAT IS GIVEN BY:
    // [TYPE]|[DONE]|[DESCRIPTION]|[DATETIME (if applicable)]
    // TYPE: T,E,D
    // DONE: 1 or 0
    public void saveData(ArrayList<DukeTask> dataList) throws IOException {
        StringBuilder dataString = new StringBuilder();
        for (DukeTask task : dataList) {
            String addition = "";
            if (task instanceof TodoTask) {
                addition = "T" + FILE_DATA_SEPARATOR
                        + (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR
                        + task.getDescription();
            } else if (task instanceof EventTask) {
                addition = "E" + FILE_DATA_SEPARATOR
                        + (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR
                        + task.getDescription() + FILE_DATA_SEPARATOR
                        + ((EventTask) task).getDatetime();
            } else if (task instanceof DeadlineTask) {
                addition = "D" + FILE_DATA_SEPARATOR
                        + (task.getDoneStatus() ? "1" : "0") + FILE_DATA_SEPARATOR
                        + task.getDescription() + FILE_DATA_SEPARATOR
                        + ((DeadlineTask) task).getDatetime();
            }
            dataString.append(addition).append(System.lineSeparator());
        }

        String output = dataString.toString();

        FileWriter writer = new FileWriter(filePath);
        writer.write(output);
        writer.close();
    }


}
