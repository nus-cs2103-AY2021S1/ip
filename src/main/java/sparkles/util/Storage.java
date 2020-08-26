package sparkles.util;

import sparkles.SparklesException;
import sparkles.task.Deadline;
import sparkles.task.Event;
import sparkles.task.Task;
import sparkles.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represent a storage object, taking in the file path of the task.txt.
 * Task.txt is a file stored on the local disk
 * with the tasks that user input.
 * Deals with loading tasks from the file and saving task in the file.
 */
public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Update task.txt.
     * Task.txt is overwritten with respect to the
     * tasks in the list provided as parameter.
     * @param storage, list of task
     */
    public void updateFile(List<Task> storage) {
        String userDir = new File("").getAbsolutePath();
        String taskFilePath = userDir + File.separator + filePath;
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(taskFilePath);
            StringBuilder append = new StringBuilder();
            for (Task task : storage) {
                append.append(task.convertToDiskFormat()).append("\n");
            }

            fileWriter.write(append.toString());
            fileWriter.close();
        } catch (IOException ignored) {
        }

    }

    /**
     * Reads task.txt
     * @return a list with the corresponding task.
     * @throws SparklesException
     */
    public List<Task> load() throws SparklesException {
        List<Task> taskList = new ArrayList<>();

        String userDir = new File("").getAbsolutePath();
        String taskFilePath = userDir + File.separator + filePath;
        File file = new File(taskFilePath);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Task task = decodeTask(sc.nextLine());
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            createFolder(taskFilePath);
            throw new SparklesException("");
        }
        return taskList;
    }

    private void createFolder(String taskFilePath) {
        File file = new File(taskFilePath);

        File parent = file.getParentFile();
        if (!parent.exists()) {
            if (parent.mkdir()) {
                createFile(file);
            } else {
                System.out.println("     Task.txt did not exist, cannot create parent file");
            }
        } else {
            createFile(file);
        }
    }

    private void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("     Task.txt did not exist, now created");
            } else {
                System.out.println("     Task.txt did not exist and cannot be created in directory");
            }
        } catch (IOException ignored) {
        }
    }

    private Task decodeTask(String next) {
        Task task;

        next = next.substring(5);
        String[] arr = next.split(" \\| ");

        if (arr[0].equals("T")) {
            task = new Todo(arr[2]);
        } else if (arr[0].equals("D")) {
            task = new Deadline(arr[2], arr[3]);
        } else {
            task = new Event(arr[2], arr[3]);
        }
        if (arr[1].equals("O")) {
            task.markAsDone();
        }
        return task;
    }
}
