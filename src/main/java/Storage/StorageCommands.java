package Storage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import TaskList.TaskList;
import Parser.Parser;


import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageCommands {
    // method to update and save the txt file with the taskList
    public static void saveTasks(){
        String directory = System.getProperty("user.dir");
        Path path =  Paths.get(directory, "data");
        try{
            // Check if the file path exists, if not, create a new directory
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }

            Path filePath = Paths.get(directory,"data","taskList.txt");
            File taskFile = filePath.toFile();

            // Check if the file exists, if not, create a new file
            if(!taskFile.exists()){
                taskFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(taskFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < TaskList.getSize(); i++) {
                bufferedWriter.write(TaskList.getTask(i).getOriginal());
                bufferedWriter.write("~");
                if(TaskList.getTask(i).getStatus()){
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
    // method to load the existing file and update the taskList
    public static void loadTasks() {
        String directory = System.getProperty("user.dir");
        Path filePath = Paths.get(directory, "data", "taskList.txt");
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
                            TaskList.addTask(newTask);

                        } else if (Parser.isEvent(nameList[0].trim().toLowerCase())) {
                            String[] task_event = nameList[1].trim().split("/at", 2);
                            Task newTask = new Event(task_event[0].trim(), task_event[1].trim(),checkDone(doneList[1]));
                            TaskList.addTask(newTask);

                        } else if (Parser.isToDo(nameList[0].toLowerCase())) {
                            Task newTask = new ToDo(nameList[1].trim(), checkDone(doneList[1]));
                            TaskList.addTask(newTask);
                        }
                    }
                }

            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        } else {
            File createFile = new File("/data/duke.txt");
        }
    }

    // check if the task was done before
    private static boolean checkDone (String checker){
        return checker.equals("1");
    }
}
