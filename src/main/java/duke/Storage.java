package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents a class to manage the storing of the TaskList into a text file,
 * and reading a text file to create a TaskList.
 */
public class Storage {

    /** File path where the text file is stored. */
    private Path filepath;

    /** Represents time and date. */
    private static final SimpleDateFormat DATE_TIME_CONVERTER_FORMAT = new SimpleDateFormat("MMM dd yyyy hh:mma");
    /** Represents date. */
    private static final SimpleDateFormat DATE_CONVERTER_FORMAT = new SimpleDateFormat("MMM dd yyyy");

    /**
     * Initialises Storage with filepath where text file is stored.
     * @param filepath Filepath where the text file is stored.
     */
    public Storage(String filepath) {
        this.filepath = Paths.get(System.getProperty("user.dir") + filepath);
    }

    /**
     * Reads existing text file and converts the text into tasks to be placed
     * into an ArrayList. If the text file does not exists, it is created.
     * @return an ArrayList containing the tasks from the text file.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        //From https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
        if (!Files.isRegularFile(filepath)) {
            createFile();
        } else {
            File f = new File(filepath.toString());
            try {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String string = s.nextLine();
                    String[] arr = string.split(" \\| ");

                    boolean isDone = arr[1].equals("1");
                    boolean isTime;
                    Date date;
                    switch (arr[0]) {
                    case "T":
                        tasks.add(new ToDo(arr[2], isDone));
                        break;
                    case "D":
                        isTime = arr[4].equals("1");
                        date = (isTime) ? DATE_TIME_CONVERTER_FORMAT.parse(arr[3]) : DATE_CONVERTER_FORMAT.parse(arr[3]);
                        tasks.add(new Deadline(arr[2], date, isTime, isDone));
                        break;
                    case "E":
                        isTime = arr[4].equals("1");
                        date = (isTime) ? DATE_TIME_CONVERTER_FORMAT.parse(arr[3]) : DATE_CONVERTER_FORMAT.parse(arr[3]);
                        tasks.add(new Event(arr[2], date, isTime, isDone));
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file :(");
                createFile();
            } catch (ParseException e) {
                System.out.println("Unable to parse date :(");
            }
        }
        return tasks;
    }

    /**
     * Creates the text file to contain the tasks at the filepath where
     * it is supposed to be.
     */
    public void createFile() {
        if (!Files.isDirectory(filepath.getParent())) {
            try {
                Files.createDirectory(filepath.getParent());
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        try {
            Files.createFile(filepath);
        } catch (IOException e) {
            System.out.println("Failed to create file");
        }
    }

    /**
     * Converts the tasks into String to be stored into the text file.
     * @param arrayList ArrayList containing the tasks.
     */
    public void writeToFile(ArrayList<Task> arrayList) {
        try {
            FileWriter fw = new FileWriter(filepath.toString());
            for (Task task : arrayList) {
                fw.write(task.toStoredTextString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }
}
