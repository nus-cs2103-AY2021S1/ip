package duke.helpers;

import duke.errors.DukeException;
import duke.errors.FIleEmptyException;
import duke.errors.FileAbsentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file and stores shortcuts
 */
public class Storage {
    private String filePath;
    private String shortFormsFilePath;
    /**
     * Constructor assigns filePath to filePath
     * @param filePath assigns this value to variable
     */
    public Storage(String filePath, String shortFormsFile) {
        this.filePath = filePath;
        this.shortFormsFilePath = shortFormsFile;
    }
    public void setShortForm() throws DukeException {
        File f = new File(this.shortFormsFilePath);
        assert f.exists(); //file should exist to keep track of tasks
        try {
            Scanner sc = new Scanner(f);
            if (sc.hasNextLine()) {
                do {
                    String shortFormString = sc.nextLine();
                    String[] shortFormDictionary = shortFormDictionary(shortFormString);
                    ShortCuts.getShortCuts().put(shortFormDictionary[0], shortFormDictionary[1]);
                } while (sc.hasNextLine());
            }
        } catch (IOException i) {
            throw new FileAbsentException(shortFormsFilePath);
        }
    }
    /**
     * Converts the string form of tasks on the file to Task objects
     *
     * @return the List of tasks containing Task instead of String
     * @throws DukeException when a file with FilePath doesnt exist.
     */
    public List<Task> load() throws DukeException {

        File f = new File(this.filePath);
        assert f.exists(); //file should exist to keep track of tasks
        try {
            List<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(f);
            if (sc.hasNext()) {
                do {
                    String input = sc.nextLine();
                    char bool = input.charAt(4); //gives the char of 1 or 0 as it is always present at index 4
                    boolean isDone = (bool == '1'); //since 1 indicates done
                    if (input.charAt(0) == 'T') {
                        ToDo todoPresent = todoPresent(input, isDone);
                        tasks.add(todoPresent);
                    } else if (input.charAt(0) == 'E') {
                        Event eventPresent = eventPresent(input, isDone);
                        tasks.add(eventPresent);
                    } else if (input.charAt(0) == 'D') {
                        Deadline deadlinePresent = deadlinePresent(input, isDone);
                        tasks.add(deadlinePresent);
                    } else {

                    }
                } while (sc.hasNextLine());
            }
            if (tasks.size() == 0) {
                throw new FIleEmptyException();
            } else {
                return tasks;
            }
        } catch (IOException error) {
            throw new FileAbsentException(this.filePath);
        }
    }

    /**
     * Returns ToDo task for that present in the list in storage
     *
     * @param input string from the file in storage
     * @param isDone boolean of whether task is completed or not. True if completed and false otherwise.
     * @return ToDo task
     */
    private ToDo todoPresent(String input, boolean isDone) {
        return new ToDo(input.substring(8), isDone); // since the string after index 8
    }

    /**
     * Returns Event task for that present in list in storage
     *
     * @param input string from file in storage
     * @param isDone boolean of whether task is completed or not. True if completed and false otherwise.
     * @return Event task
     */
    private Event eventPresent(String input, boolean isDone) {
        String string = "";
        int index = -1;
        for (int i = 8; i < input.length(); i++) {
            if (input.charAt(i) == '|') {
                index = i;
                break;
            }
            string = string + input.charAt(i); //character "|" splits the description of event and time.
        }
        String another = "";
        for (int i = index + 2; i < input.length(); i++) {
            if (input.charAt(i) == '-') {
                index = i;
                break;
            }
            another = another + input.charAt(i); // character "-" separates the start and end time.
        }
        return new Event(string, isDone, another, input.substring(index + 1));
    }
    /**
     * Returns Deadline task for that present in list in storage
     *
     * @param input string from file in storage
     * @param isDone boolean of whether task is completed or not. True if completed and false otherwise.
     * @return Deadline task
     */
    private Deadline deadlinePresent(String input, boolean isDone) {
        String string = "";
        int index = -1;
        for (int i = 8; i < input.length(); i++) {
            if (input.charAt(i) == '|') {
                index = i;
                break;
            }
            string = string + input.charAt(i); //line '|' splits the description of deadline and time.
        }
        return new Deadline(string, isDone, input.substring(index + 2));

    }
    /**
     * gives the filePath
     * @return the value of filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This splits the short form into the original form and short form
     *
     * @param shortFormString is the String input by user
     * @return String array containing the original and the short form of keywords
     */
    public String[] shortFormDictionary(String shortFormString) {
        String shortForm = "";
        int index = -1;
        for (int i = 0; i < shortFormString.length(); i++) {
            if (shortFormString.charAt(i) == ' ') {
                index = i;
                break;
            }
            shortForm = shortForm + shortFormString.charAt(i);
        }
        return new String[]{shortForm, shortFormString.substring(index + 1)};
    }
    public String getShortFormsFilePath() {
        return shortFormsFilePath;
    }
}
