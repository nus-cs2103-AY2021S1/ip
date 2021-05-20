package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Storage class in charge of reading and writing data to a text file.
 */
public class Storage {

    /** The directory path of the text file Duke reads and saves to. */
    private final String filePath;

    /**
     * Constructs a Storage object responsible for reading and writing of data to hard disk.
     *
     * @param filePath The directory path of the text file Duke reads and saves to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the index of a specified character (first instance) in a string
     *
     * @param s The String input.
     * @param target The character input.
     * @return index.
     */
    public static int getPosition(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Reads the text file into ArrayList format to start the program.
     *
     * @return ArrayList that will be the TaskList.
     * @throws FileNotFoundException exception if file isn't found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> newList = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
            // char isDone = line.charAt(4);
            switch (taskType) {
            case 'T':
                newList.add(new Todo(line.substring(7)));
                break;
            case 'E':
                int posE = getPosition(line, '/');
                newList.add(new Event(line.substring(7, posE), line.substring(posE + 4)));
                break;
            case 'D':
                int posD = getPosition(line, '/');
                newList.add(new Deadline(line.substring(7, posD), line.substring(posD + 4)));
                break;
            default:
                System.out.println("Can't read line - load()");
            }
        }
        return newList;
    }

    /**
     * Saves the tasks to a text file.
     *
     * @param ls the TaskList used.
     * @throws IOException Exception.
     */
    public void writeToFile(ArrayList<Task> ls) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder textToAdd = new StringBuilder();
        // convert ArrayList contents to string
        for (Task t : ls) {
            if (t instanceof Event || t instanceof Deadline) {
                textToAdd.append(t.toWrite()).append("\n");
            } else {
                textToAdd.append(t.toString()).append("\n");
            }
        }
        fw.write(textToAdd.toString());
        fw.close();
    }
}
