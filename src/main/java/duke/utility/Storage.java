package duke.utility;

import duke.DukeException;
import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    /** duke.task.TaskList class that stores and deals with the tasks **/
    private TaskList taskList;
    private Statistic statistic;
    /** Path to where the file is stored **/
    private Path storageTaskPath;
    private Path storageStatisticPath;

    /**
     *Class constructor
     *
     * @throws DukeException If the file is unable to be opened
     */

    public Storage(TaskList taskList, Statistic statistic) throws DukeException {
        this.taskList = taskList;
        this.statistic = statistic;
        storageTaskPath = Paths.get(".", "data", "test.txt");
        storageStatisticPath = Paths.get(".","data","statistic.txt");
        getPath("test.txt");
        getPath("statistic.txt");
        loadTaskFile();
        loadStatisticFile();
    }

    private void getPath(String fileName){
        Path path = Paths.get(".", "data", fileName);
        try {
            // Create directory if needed
            Path parentPath = path.getParent();
            Files.createDirectories(parentPath);

            if (!Files.exists(path)) {
                Files.createFile(path);
            }

        } catch (IOException e) {
            System.out.println("unable to read file " + e.getMessage());
        }
    }

    /**
     * Open and read the stored tasks from the hard drive
     * add the stored tasks to taskList
     *
     * @throws DukeException If the file is unable to be opened
     */
    private void loadTaskFile() throws DukeException {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(storageTaskPath.toString()));
            String task = bf.readLine();
            String[] inputs;
            while (task != null) {
                inputs = task.split(" \\| ", 4);
                String taskType = inputs[0];
                Task newTask;
                try {
                    switch (taskType) {
                    case "T": {
                        newTask = new Todo(inputs[2]);
                        break;
                    }
                    case "D": {
                        newTask = Deadline.create(inputs[2], inputs[3]);
                        break;
                    }
                    case "E": {
                        newTask = Event.create(inputs[2], inputs[3]);
                        break;
                    }
                    default: {
                        throw new DukeException("smlj??????");
                    }

                    }

                    if (inputs[1].equals("1")) {
                        newTask.complete();
                    }
                    taskList.addTask(newTask, false);
                    task = bf.readLine();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            throw new DukeException("unable to load file");
        }

    }

    private void loadStatisticFile() throws DukeException {

        try {
            BufferedReader bf = new BufferedReader(new FileReader(storageStatisticPath.toString()));
            String task = bf.readLine();
            String[] inputs;
            int weekNum = -1;
            while (task != null) {
                inputs = task.split("#D#", 2);
                if (inputs.length == 2) {
                    weekNum = Integer.parseInt(inputs[1]);
                } else {
                    statistic.addPastTask(inputs[0], weekNum);
                }
                task = bf.readLine();
            }
        } catch(IOException e){
            throw new DukeException("unable to open file");
        }
    }


    /**
     * Write the stored tasks in taskList into a file stored in the hard drive
     *
     * @throws DukeException If the tasks are unable to be saved into the file
     */
    public void saveTaskFile() throws DukeException {
        try {
            FileWriter fwTask = new FileWriter(storageTaskPath.toString());
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                fwTask.write(taskList.getTask(i).safeFileFormat());
            }
            fwTask.close();
            FileWriter fwStatistic = new FileWriter(storageStatisticPath.toString());
            fwStatistic.write(statistic.safeFileFormat());
            fwStatistic.close();
        } catch (IOException e) {
            throw new DukeException("unable to save file");
        }
    }



}
