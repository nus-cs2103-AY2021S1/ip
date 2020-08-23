package duke;

import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Task;
import duke.Task.ToDo;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the filePath specified
     * @param filePath specifies the directory of the csv file to read from/write to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        initialize();
    }

    /**
     * Returns an ArrayList of Task from file located in filePath
     * @return ArrayList of Task
     */
    public ArrayList<Task> read() {
        if (isEmpty()) {
            // if file is totally new
            try {
                FileWriter csvWriter = insertColumnHeadersToCSV();
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<Task>();
        } else {
            // if csv file has existing data
            ArrayList<Task> taskList = new ArrayList<>();
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

                // does once to remove column headers
                String row = csvReader.readLine();

                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    // data[0] = type_of_task
                    // data[1] = is_completed
                    // data[2] = task_description
                    // data[3] = time_description (only applicable to deadline and event)
                    String typeOfTask = data[0];
                    String isCompleted = data[1];
                    String taskDescription = data[2];
                    String timeDescription = "";
                    if (data.length > 3) {
                        timeDescription = data[3];
                    }

                    if (typeOfTask.equals("Event")) {
                        String[] temp = timeDescription.split(" ");
                        int day = Integer.parseInt(temp[0]);
                        int month = Integer.parseInt(temp[1]);
                        int year = Integer.parseInt(temp[2]);
                        int hour = Integer.parseInt(temp[3]);
                        int min = Integer.parseInt(temp[4]);

                        Event event = new Event(taskDescription, LocalDateTime.of(year, month, day, hour, min));
                        if (isCompleted.equals("true")) {
                            event.completeTask();
                        }
                        taskList.add(event);
                    } else if (typeOfTask.equals("Deadline")) {
                        String[] temp = timeDescription.split(" ");
                        int day = Integer.parseInt(temp[0]);
                        int month = Integer.parseInt(temp[1]);
                        int year = Integer.parseInt(temp[2]);
                        int hour = Integer.parseInt(temp[3]);
                        int min = Integer.parseInt(temp[4]);

                        Deadline dl = new Deadline(taskDescription, LocalDateTime.of(year, month, day, hour, min));
                        if (isCompleted.equals("true")) {
                            dl.completeTask();
                        }
                        taskList.add(dl);
                    } else if (typeOfTask.equals("ToDo")) {
                        ToDo toDo = new ToDo(taskDescription);
                        if (isCompleted.equals("true")) {
                            toDo.completeTask();
                        }
                        taskList.add(toDo);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return taskList;
        }
    }

    /**
     * Saves all the tasks inside taskList into the csv located in filePath
     * @param taskList TaskList object
     * @throws IOException If filePath does not exist
     */
    public void saveToCSV(TaskList taskList) throws IOException {
        FileWriter csvWriter = insertColumnHeadersToCSV();
        for(Task task : taskList.getList()) {
            String typeOfTask = task.getClass().getSimpleName();
            String isCompleted = Boolean.toString(task.isCompleted());
            String taskDescription = task.getTaskDescription();
            String timeDescription = "";
            if (typeOfTask.equals("Deadline")) {
                Deadline dl = (Deadline) task;
                timeDescription = dl.getDateTime();
            } else if (typeOfTask.equals("Event")) {
                Event event = (Event) task;
                timeDescription = event.getDateTime();
            }
            csvWriter.append(typeOfTask);
            csvWriter.append(",");
            csvWriter.append(isCompleted);
            csvWriter.append(",");
            csvWriter.append(taskDescription);
            csvWriter.append(",");
            csvWriter.append(timeDescription);
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    private FileWriter insertColumnHeadersToCSV() throws IOException {
        // writes column headers in
        FileWriter csvWriter = new FileWriter(filePath);
        csvWriter.append("typeOfTask");
        csvWriter.append(",");
        csvWriter.append("isCompleted");
        csvWriter.append(",");
        csvWriter.append("taskDescription");
        csvWriter.append(",");
        csvWriter.append("timeDescriptionInDDMMYYYYHHMM");
        csvWriter.append("\n");
        return csvWriter;
    }

    /**
     * Checks if csv file in filePath is empty
     * @return true if empty, false if not empty
     */
    public boolean isEmpty() {
        return new File(filePath).length() == 0;
    }

    private void initialize() {
        BufferedReader csvReader = null;
        File dataFolder = new File("data");
        if (dataFolder.exists()) {
            // if data folder exists
            try {
                // try to read duke.txt in filePath
                new BufferedReader(new FileReader(filePath));
            } catch (FileNotFoundException e) {
                // if csv file has not been created yet
                try {
                    // creates the csv file
                    FileWriter csvWriter = new FileWriter(filePath);
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException IOError) {
                    IOError.printStackTrace();
                }
            }
        } else {
            // if data folder does not exist
            dataFolder.mkdir(); // makes the data folder
            try {
                // creates the csv file
                FileWriter csvWriter = new FileWriter(filePath);
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException IOError) {
                IOError.printStackTrace();
            }
        }
    }
}
