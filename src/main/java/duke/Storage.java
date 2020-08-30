package duke;

import duke.exception.StorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final String filePath = "data/tasks.txt";
    private File storage;

    public Storage() {
        this.storage = new File(filePath);
    }

    public ArrayList<String> readTaskStorage() throws StorageException {
        ArrayList<String> existingTasks = new ArrayList<>();
        try {
            if(this.storage.exists()){
                // Load into taskList if file is not empty
                Scanner s = new Scanner(this.storage);
                if (this.storage.length() != 0){
                    while (s.hasNext()) {
                        existingTasks.add(s.nextLine());
                    }
                }
            } else {
                this.storage.getParentFile().mkdirs();
                this.storage.createNewFile();
            }

            if(!this.storage.exists()){
                throw new IOException();
            }
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to find the tasks you saved previously ;A;");
        }
        return existingTasks;
    }

    public void appendTaskStorage(String taskString) throws StorageException {
        try {
            FileWriter appender = new FileWriter(filePath, true);
            appender.write(taskString);
            appender.close();
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to save this task ;A;");
        }
    }

    public void writeTaskStorage(String taskString) throws StorageException {
        try {
            FileWriter rewriter = new FileWriter(filePath);
            rewriter.write(taskString);
            rewriter.close();
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to modify the tasks you saved previously ;A;");
        }
    }
}
