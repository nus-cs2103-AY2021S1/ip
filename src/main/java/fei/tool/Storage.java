package fei.tool;

import fei.exception.FeiException;
import fei.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String path) {
        this.filePath = path;
    }

    /**
     * This method load the data from the hard disk when Alison starts up.
     * @return TaskList converted from text file.
     * @throws FeiException when Alison fails to load the file.
     */
    public TaskList load() throws FeiException {
        TaskList tasks = new TaskList();
        try {
            File dataFile = new File(filePath);
            Scanner sc = new Scanner(dataFile);

            while (sc.hasNextLine()) {
                Task saved = Parser.parseTask(sc.nextLine());
                if (saved != null) {
                    tasks.add(saved);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw FeiException.loadingException();
        }
        return tasks;
    }

    /**
     * This methods update the tasks list in your file automatically whenever the task list changes.
     * @param tasks task list store in the bot.
     * @throws FeiException when Alison fail to update the file upon the change.
     */
    public void update(TaskList tasks) throws FeiException {
        try {
            FileWriter writer = new FileWriter("./tasks.txt", false);

            for (Task task : tasks) {
                writer.write(task.savedFormat() + '\n');
            }
            writer.close();

        } catch (IOException e) {
            throw FeiException.writingException();
        }
    }

}
