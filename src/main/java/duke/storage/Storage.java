package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import duke.taskList.TaskList;
import duke.parser.Parser;


import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A Storage class creates a storage object that
 * Saves the TaskList of Tasks into a txt file
 * Loads the list of Tasks from a txt file into a TaskList Object
 */
public class Storage {

    public static String directory;

    /**
     * Creates a storage object
     *
     * @param directory
     */
    public Storage(String directory){

        this.directory = directory;
    }


    /**
     * Saves the list of Tasks in the TaskList object into a txt file
     *
     * @param taskList
     */
    public void saveTasks(TaskList taskList){
        Path path =  Paths.get(this.directory, "Data");
        try{
            // Check if the file path exists, if not, create a new directory
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }

            Path filePath = Paths.get(directory,"Data","taskList.txt");
            File taskFile = filePath.toFile();

            // Check if the file exists, if not, create a new file
            if(!taskFile.exists()){
                taskFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(taskFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < taskList.getSize(); i++) {
                bufferedWriter.write(taskList.getTask(i).getOriginal());
                bufferedWriter.write("~");
                if(taskList.getTask(i).getStatus()){
                    bufferedWriter.write("1");
                } else{
                    bufferedWriter.write("0");
                }
                bufferedWriter.write("++");
            }
            //System.out.println(SAVED);
            bufferedWriter.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the list of Tasks in the txt file into the TaskList object
     *
     * @param taskList
     */
    public void loadTasks(TaskList taskList) {
        Path filePath = Paths.get(this.directory, "Data", "taskList.txt");

        if(filePath.toFile().exists()){
            try{
                BufferedReader reader = Files.newBufferedReader(filePath);
                String data = reader.readLine();
                if(data != null) {
                    String[] tasks = data.split("\\+\\+");
                    for (int i = 0; i < tasks.length; i++) {
                        //split the individual tasks
                        String[] doneList = tasks[i].split("~", 2);
                        String[] nameList= doneList[0].split(" ", 2);
                        if (Parser.isDeadline(nameList[0].trim().toLowerCase())){
                            String[] task_deadline = nameList[1].trim().split("/by", 2);
                            Task newTask = new Deadline(task_deadline[0].trim(),
                                    task_deadline[1].trim(), checkDone(doneList[1]));
                            taskList.addTask(newTask);

                        } else if (Parser.isEvent(nameList[0].trim().toLowerCase())) {
                            String[] task_event = nameList[1].trim().split("/at", 2);
                            Task newTask = new Event(task_event[0].trim(), task_event[1].trim(),checkDone(doneList[1]));
                            taskList.addTask(newTask);

                        } else if (Parser.isToDo(nameList[0].toLowerCase())) {
                            Task newTask = new ToDo(nameList[1].trim(), checkDone(doneList[1]));
                            taskList.addTask(newTask);
                        }
                    }
                }

            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        } else {
            File createFile = new File("/Data/duke.txt");
        }
    }

    // check if the task was done before
    private static boolean checkDone (String checker){
        return checker.equals("1");
    }
}
