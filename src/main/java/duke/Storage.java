package duke;

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

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a class to manage the storing of the TaskList into a text file,
 * and reading a text file to create a TaskList.
 */
public class Storage {


    /**
     * Represents time and date.
     */
    private static final SimpleDateFormat DATE_TIME_CONVERTER_FORMAT = new SimpleDateFormat("MMM dd yyyy hh:mma");
    /**
     * Represents date.
     */
    private static final SimpleDateFormat DATE_CONVERTER_FORMAT = new SimpleDateFormat("MMM dd yyyy");


    /**
     * File path where the text file is stored.
     */
    private Path filepath;
    /**
     * File path where the archive file is stored
     */
    private Path archivePath;

    /**
     * Initialises Storage with filepath where text file is stored.
     *
     * @param filepath    Filepath where the text file is stored.
     * @param archivePath Filepath where the archive file is stored.
     */
    public Storage(String filepath, String archivePath) {
        this.filepath = Paths.get(System.getProperty("user.dir") + filepath);
        this.archivePath = Paths.get(System.getProperty("user.dir") + archivePath);
    }

    /**
     * Read the main text file and convert into ArrayList of Tasks.
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> readMain() {
        return readFile(filepath);
    }

    /**
     * Read the archive text file and convert into ArrayList of Archived Tasks.
     *
     * @return ArrayList of Archived Tasks
     */
    public ArrayList<Task> readArchive() {
        return readFile(archivePath);
    }

    /**
     * Reads text file and converts the text into tasks to be placed
     * into an ArrayList. If the text file does not exists, it is created.
     *
     * @param path file path where text file is stored
     * @return an ArrayList containing the tasks from the text file.
     */
    private ArrayList<Task> readFile(Path path) {
        ArrayList<Task> tasks = new ArrayList<>();
        //From https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
        if (!Files.isRegularFile(path)) {
            createFile(path);
        } else {
            File f = new File(path.toString());
            try {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String string = s.nextLine();
                    String[] arr = string.split(" \\| ");
                    parseArrayString(arr, tasks);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file :(");
                createFile(path);
            } catch (ParseException e) {
                System.out.println("Unable to parse date :(");
            }
        }
        return tasks;
    }

    /**
     * @param arr   A line from the text file split into an array.
     * @param tasks ArrayList of tasks.
     * @throws ParseException if incorrect date format.
     */
    private void parseArrayString(String[] arr, ArrayList<Task> tasks) throws ParseException {
        boolean isDone = arr[1].equals("1");
        boolean isTime;
        Date date;
        try {
            assert arr[0].equals("T") || arr[0].equals("D") || arr[0].equals("E");
            switch (arr[0]) {
            case "T":
                tasks.add(new ToDo(arr[2], isDone));
                break;
            case "D":
                isTime = arr[4].equals("1");
                date = (isTime) ? DATE_TIME_CONVERTER_FORMAT.parse(arr[3])
                        : DATE_CONVERTER_FORMAT.parse(arr[3]);
                tasks.add(new Deadline(arr[2], date, isTime, isDone));
                break;
            case "E":
                isTime = arr[4].equals("1");
                date = (isTime) ? DATE_TIME_CONVERTER_FORMAT.parse(arr[3])
                        : DATE_CONVERTER_FORMAT.parse(arr[3]);
                tasks.add(new Event(arr[2], date, isTime, isDone));
                break;
            default:
                assert false;
            }
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * Creates the text file to contain the tasks at the filepath where
     * it is supposed to be.
     *
     * @param path path of text file.
     */
    private void createFile(Path path) {
        if (!Files.isDirectory(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
            } catch (IOException e) {
                System.out.println("Failed to create directory");
            }
        }

        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Failed to create file");
        }
    }

    /**
     * Converts the tasks into String to be stored into the text file.
     *
     * @param arrayList ArrayList containing the tasks.
     * @param path path of file.
     */
    private void writeToFile(ArrayList<Task> arrayList, Path path) {
        try {
            FileWriter fw = new FileWriter(path.toString());
            for (Task task : arrayList) {
                fw.write(task.toStoredTextString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    /**
     * Write into the Main file contained at filepath.
     * @param arrayList ArrayList of Tasks that the text file is being written to.
     */
    public void writeToMain(ArrayList<Task> arrayList) {
        writeToFile(arrayList, filepath);
    }

    /**
     * Write into the Main file contained at archivePath.
     * @param arrayList ArrayList of Tasks that the text file is being written to.
     */
    public void writeToArchive(ArrayList<Task> arrayList) {
        writeToFile(arrayList, archivePath);
    }


}
