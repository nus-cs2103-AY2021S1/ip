package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Paths;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    private final File dataDirectory;
    private final File dataFile;
    private final String dataFilePath;

    public Storage(String fileName) {
        String dataDirectoryPath = Paths.get("data").toString();
        dataDirectory = new File(dataDirectoryPath);
        dataFilePath = Paths.get("data", fileName).toString();
        dataFile = new File(dataFilePath);
    }

    public void loadData(TaskList taskList) throws DukeException {
        this.checkDataDirectoryExist();
        boolean toLoadDataFile = this.checkDataFileExist();
        if (toLoadDataFile) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] taskInputs = line.split("\\|");
                    String taskType = taskInputs[0].trim();
                    Task t;

                    switch (taskType) {
                    case "T":
                        t = new ToDo(taskInputs[2].trim());
                        break;
                    case "D":
                        t = new Deadline(taskInputs[2].trim(), taskInputs[3].trim());
                        break;
                    case "E":
                        t = new Event(taskInputs[2].trim(), taskInputs[3].trim());
                        break;
                    default:
                        throw new DukeException("☹ Sorry, I don't recognise that command from the data "
                                + "file!");
                    }
                    if (taskInputs[1].trim().equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                }
                br.close();
            } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
            }
        }
    }

    public void checkDataDirectoryExist() {
        try {
            FileReader readFile = new FileReader(dataDirectory);
        } catch (FileNotFoundException e) {
            File newDataDirectory = new File(String.valueOf(dataDirectory));
            if(!newDataDirectory.exists()) {
                newDataDirectory.mkdir();
            }
        }
    }

    public boolean checkDataFileExist() {
        boolean toLoadDataFile = true;
        try {
            if(this.dataFile.createNewFile()) {
                toLoadDataFile = false;
                Ui.newFileCreated();
            }
        } catch (IOException e) {
            toLoadDataFile = false;
        }
        return toLoadDataFile;
    }

    public void saveData(ArrayList<Task> taskList) {
        String dataFilePath = Paths.get("data", "duke.txt").toString();
        try {
            StringBuilder content = new StringBuilder();
            FileWriter fw = new FileWriter(dataFilePath);
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    String taskDetails = ((ToDo) task).saveToDo();
                    content.append(taskDetails).append("\n");
                } else if (task instanceof Deadline) {
                    String taskDetails = ((Deadline) task).saveDeadline();
                    content.append(taskDetails).append("\n");
                } else if (task instanceof Event){
                    String taskDetails = ((Event) task).saveEvent();
                    content.append(taskDetails).append("\n");
                }
            }
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task newTask) {
        String dataFilePath = Paths.get("data", "duke.txt").toString();
        try {
            StringBuilder content = new StringBuilder();
            FileWriter fw = new FileWriter(this.dataFilePath, true);
            if (newTask instanceof ToDo) {
                String taskDetails = ((ToDo) newTask).saveToDo();
                content.append(taskDetails).append("\n");
            } else if (newTask instanceof Deadline) {
                String taskDetails = ((Deadline) newTask).saveDeadline();
                content.append(taskDetails).append("\n");
            } else if (newTask instanceof Event){
                String taskDetails = ((Event) newTask).saveEvent();
                content.append(taskDetails).append("\n");
            }
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}