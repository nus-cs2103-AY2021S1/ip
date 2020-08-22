package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File taskFile;

    public Storage(String filepath) throws DukeException {
        this.taskFile = new File(filepath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            taskFile.getParentFile().mkdirs();
            boolean doesNotExist = taskFile.createNewFile();
            if (doesNotExist) {
                throw new DukeException("Database file does not exist");
            }
            Scanner loadedData = new Scanner(taskFile);

            while (loadedData.hasNextLine()) {
                String[] taskParts = loadedData.nextLine().split("~");
                String identifier = taskParts[0];
                String desc = taskParts[2];
                LocalDateTime timing = taskParts.length == 3 ? null : LocalDateTime.parse(taskParts[3]);
                boolean isDone = Boolean.parseBoolean(taskParts[1]);
                Task savedTask;

                if (identifier.equals("T")) {
                    savedTask = new Todo(desc, isDone);
                } else if (identifier.equals("D")) {
                    savedTask = new Deadline(desc, timing, isDone);
                } else {
                    savedTask = new Event(desc, timing, isDone);
                }

                tasks.add(savedTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public void append(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(taskFile.getPath(), true);
            fw.write(task.saveFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException((e.getMessage()));
        }
    }

    public void overwrite(TaskList todos) throws DukeException {
        try {
            FileWriter fw = new FileWriter(taskFile.getPath());
            Task todo;
            for (int i = 0; i < todos.size(); i++) {
                todo = todos.get(i);
                fw.write(todo.saveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
