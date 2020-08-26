package com.Duke.DataManager;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.Tasks.Deadline;
import com.Duke.Tasks.Event;
import com.Duke.Tasks.Task;
import com.Duke.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static void write(TaskList taskList) throws DukeException {
        String homeDir = System.getProperty("user.dir");
        Path dataFolderPath = Paths.get(homeDir, "data");
        if(Files.exists(dataFolderPath)){
            try {
                Files.createDirectories(dataFolderPath);
            }catch (Exception e){
                throw new DukeException("Unable to create a files for storage");
            }
        }
        Path dataFilePath = Paths.get(homeDir, "data", "DukeData.txt");
        File dukeData = dataFilePath.toFile();
        if (!dukeData.exists()) {
            try {
                dukeData.createNewFile();
            }catch (Exception e){
                throw new DukeException("Unable to create a files for storage");
            }
        }
        try {
            FileWriter writer = new FileWriter(dukeData);
            for (Task item : taskList.getListOfTasks()) {
                writer.write(item.toSaveFormat() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new DukeException("Error saving files");
        }
    }

    public static List<Task> read(){
        List<Task> taskList = new ArrayList<Task>();
        try {
            String homeDir = System.getProperty("user.dir");
            Path filePath = Paths.get(homeDir, "data", "DukeData.txt");
            File file = filePath.toFile();
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                String[] dataArr = data.split("\\*");
                if(dataArr[0] == "T"){
                    if(dataArr[2] == "Y"){
                        taskList.add(new ToDo(dataArr[1], true));
                    }else{
                        taskList.add(new ToDo(dataArr[1], false));
                    }
                }else if (dataArr[0]=="E"){
                    if(dataArr[4] == "Y"){
                        taskList.add(new Event(dataArr[1], dataArr[2], dataArr[3], true));
                    }else{
                        taskList.add(new Event(dataArr[1], dataArr[2], dataArr[3], false));
                    }
                }else if(dataArr[0]=="D"){
                    if(dataArr[4] == "Y"){
                        taskList.add(new Deadline(dataArr[1], dataArr[2], true));
                    }else{
                        taskList.add(new Deadline(dataArr[1], dataArr[2], false));
                    }
                }else{
                    if(dataArr[2] == "Y"){
                        taskList.add(new Task(dataArr[1], true));
                    }else{
                        taskList.add(new Task(dataArr[1], false));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return taskList;
        } catch (DukeException e){
            return taskList;
        }
        return taskList;
    }
}
