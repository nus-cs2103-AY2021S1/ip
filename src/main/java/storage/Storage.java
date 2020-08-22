package storage;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import parser.TaskParser;
import service.DukeService;
import service.Task;
import utils.TokenUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage reads and writes data to a text file
 */
public class Storage {
    private String fileDirectory;
    private ArrayList<Task> tasks;
    private TaskParser parser;

    /**
     * Constructor
     * @param fileDirectory: relative directory
     * @param parser: task parser, a helper to parse tasks to strings vice and versa
     */
    public Storage(String fileDirectory, TaskParser parser) {
        tasks = new ArrayList<>();
        this.fileDirectory = fileDirectory;
        this.parser = parser;
    }

    private String taskToString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.isDone ? "1" : "0")
          .append(" | ")
          .append(task.taskWord)
          .append(" | ")
          .append(TokenUtils.tokensToString(task.tokens));
        return sb.toString();
    }

    private Task stringToTask(String raw) throws InvalidCommandException {
        String[] tokens = raw.split(" \\| ");
        boolean isDone = false;
        if (tokens[0].equals("1")) {
            isDone = true;
        }
        tokens = TokenUtils.dropFirst(tokens);

        Task newTask = this.parser.parse(tokens);
        newTask.parse();
        if (isDone) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * @param service: DukeService to get all tasks
     * @throws DukeException throws exception if reading error happens
     */
    public void readFromFile(DukeService service) throws DukeException {
        try {
            File file = new File(fileDirectory);
            File directory = new File(file.getParentFile().getAbsolutePath());
            directory.mkdirs();
            file.createNewFile();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                this.tasks.add(stringToTask(data));
            }
            service.addInitializedTasks(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * @param service: DukeService to put all tasks
     * @throws DukeException: throws exception
     */
    public void writeToFile(DukeService service) throws DukeException {
        String[] parsedStrings = service.getParsedTasks(this::taskToString);
        try {
            File file = new File(fileDirectory);
            File directory = new File(file.getParentFile().getAbsolutePath());
            directory.mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(fileDirectory);
            for (String parsedString : parsedStrings) {
                writer.write(parsedString);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ignored) {
        }
    }
}
