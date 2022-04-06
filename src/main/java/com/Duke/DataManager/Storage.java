package com.Duke.DataManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.Tasks.*;


/**
 * This Class manages read and write operations into the storage files
 */
public class Storage {

    /**
     * This method writes a given list of tasks into the storage file
     * @param taskList This is the taskList that is stored
     */
    public static void write(TaskList taskList) throws DukeException {
        assert taskList != null : new DukeException("Unable to create a files for storage");
        String homeDir = System.getProperty("user.dir");
        Path dataFolderPath = Paths.get(homeDir, "data");
        if (Files.exists(dataFolderPath)) {
            try {
                Files.createDirectories(dataFolderPath);
            } catch (Exception e) {
                throw new DukeException("Unable to create a files for storage");
            }
        }
        Path dataFilePath = Paths.get(homeDir, "data", "DukeData.txt");
        File dukeData = dataFilePath.toFile();
        if (!dukeData.exists()) {
            try {
                dukeData.createNewFile();
            } catch (Exception e) {
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

    /**
     * This method reads a given list of tasks and returns a List of tasks
     * @return Returns a list of Tasks that can be used to create a TaskList object
     */
    public static List<Task> read() {
        List<Task> taskList = new ArrayList<Task>();
        try {
            String homeDir = System.getProperty("user.dir");
            Path filePath = Paths.get(homeDir, "data", "DukeData.txt");
            File file = filePath.toFile();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArr = data.split("\\*");
                if ("T".equals(dataArr[0])) {
                    if (dataArr[2].equals("Y")) {
                        taskList.add(new ToDo(dataArr[1], true));
                    } else {
                        taskList.add(new ToDo(dataArr[1], false));
                    }
                } else if ("E".equals(dataArr[0])) {
                    if (dataArr[5].equals("Y")) {
                        taskList.add(new Event(dataArr[1], LocalTime.parse(dataArr[2]), LocalTime.parse(dataArr[3]), true, LocalDate.parse(dataArr[4])));
                    } else {
                        taskList.add(new Event(dataArr[1], LocalTime.parse(dataArr[2]), LocalTime.parse(dataArr[3]), false, LocalDate.parse(dataArr[4])));
                    }
                } else if ("D".equals(dataArr[0])) {
                    if (dataArr[3].equals("Y")) {
                        taskList.add(new Deadline(dataArr[1], LocalDate.parse(dataArr[2]), true));
                    } else {
                        taskList.add(new Deadline(dataArr[1], LocalDate.parse(dataArr[2]), false));
                    }
                } else if("W".equals(dataArr[0])){
                    if(dataArr[4].equals("Y")){
                        taskList.add(new DoWithin(dataArr[1], LocalDate.parse(dataArr[2]), LocalDate.parse(dataArr[3]), true));
                    }else{
                        taskList.add(new DoWithin(dataArr[1], LocalDate.parse(dataArr[2]), LocalDate.parse(dataArr[3]), false));
                    }
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            return taskList;
        }
        return taskList;
    }
}
