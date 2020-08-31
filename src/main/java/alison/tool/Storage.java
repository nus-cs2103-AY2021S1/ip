package alison.tool;

import alison.exception.AlisonException;
import alison.task.Task;

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
     * @throws AlisonException when Alison fails to load the file.
     */
    public TaskList load() throws AlisonException {
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
            throw AlisonException.loadingException();
        }
        return tasks;
    }

    /**
     * This methods update the tasks list in your file automatically whenever the task list changes.
     * @param tasks task list store in the bot.
     * @throws AlisonException when Alison fail to update the file upon the change.
     */
    public void update(TaskList tasks) throws AlisonException {
        try {
            FileWriter writer = new FileWriter("./data/tasks.txt", false);

            for (Task task : tasks) {
                writer.write(task.savedFormat() + '\n');
            }
            writer.close();

        } catch (IOException e) {
            throw AlisonException.writingException();
        }
    }

}
