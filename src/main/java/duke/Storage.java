package main.java.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public String fileDirectory;
    public String fileName;
    public File memoFile;
    public Parser parser;

    public Storage(String fileDirectory, String fileName) {
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
        this.parser = new Parser();
    }

    public void reachFile() {
        File data_folder = new File(fileDirectory);
        if (!data_folder.exists() && !data_folder.isDirectory()) {
            data_folder.mkdirs();
        }
        File task_list_file = new File(fileDirectory + fileName);
        if (!task_list_file.exists()) {
            try {
                task_list_file.createNewFile();
            } catch (Exception e) {
                HandleException.handleException(DukeException.ExceptionType.read_file);
            }
        }
        this.memoFile = task_list_file;
    }

    public List<Task> readMemoTasks() {
        reachFile();
        List<Task> task_collections = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(memoFile);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the memory cannot be read successfully.");
            return task_collections;
        }
        while (sc.hasNextLine()) {
            String curr_task = sc.nextLine();
            if (curr_task.equals("") || curr_task.isBlank()) {
                continue;
            }
            String[] task_info = this.parser.memoTaskParser(curr_task);
            switch (task_info[0]) {
                case "T":
                    task_collections.add(
                            new Todo(task_info[2], task_info[1].equals("0") ? false : true));
                    break;
                case "E":
                    task_collections.add(
                            new Event(task_info[2], task_info[3],
                                    task_info[1].equals("0") ? false : true));
                    break;
                case "D":
                    task_collections.add(
                            new Deadline(task_info[2], task_info[3],
                                    task_info[1].equals("0") ? false : true));
                    break;
            }
        }
        return task_collections;
    }

    public String taskToMemoStr(Task t) {
        String memoStr = "";
        String[] info = t.getInfo();
        memoStr += "\n" + info[0] + SpecialFormat.split_notn + info[1] + SpecialFormat.split_notn + info[2];
        if (t.getType().equals("D") || t.getType().equals("E")) {
            memoStr += SpecialFormat.split_notn + info[3];
        }
        return memoStr;
    }

    public void write_memory(List<Task> task_list) {
        try {
            FileWriter fw = new FileWriter(fileDirectory + fileName);
            String textToAppend = "";
            Iterator task_iter = task_list.iterator();
            while (task_iter.hasNext()) {
                Task t = (Task) task_iter.next();
                textToAppend += taskToMemoStr(t);
            }
            fw.write(textToAppend);
            fw.close();
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
        }
    }

    public void appendToFile(String filePath, Task t) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // appending instead of overwriting
            fw.write(taskToMemoStr(t));
            fw.close();
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
        }
    }

}
