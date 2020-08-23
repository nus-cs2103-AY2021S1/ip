package com.duke.storage;

import com.duke.events.Task;
import com.duke.exceptions.DukeException;
import com.duke.parser.Parser;
import com.duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String FILE_PATH = "src/main/data/input.txt";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void printFileContents() {
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public List<Task> load() throws DukeException {
        try{
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            List<Task> taskArr = new ArrayList<>();
            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] taskStringArr = taskString.split(" - ");
                Task task = Parser.parseTask(taskStringArr);
                taskArr.add(task);
            }
            return taskArr;
        } catch (FileNotFoundException e) {
            throw new DukeException("File failed to load. Initializing new File...");
        }
    }

    private static String parseTaskToString(Task task) {
        return task.parseToSaveFormat();
    }

    public static void saveListToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter((FILE_PATH));
        String input = "";
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        for (int i = 0; i < taskList.getList().size(); i++) {
            List<Task> list = taskList.getList();
            input += parseTaskToString(list.get(i)) + "\n";
        }
        fw.write(input);
        fw.close();
    }

}
