package dukeclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    /**
     * Parse the input String line by line and returns a task.
     * Each segment in an entry must be split by "//".
     *
     * @param input A single line of entry from the text file.
     * @return Task of a specific type e.g. todo, deadline.
     * @throws InvalidInputException  If the data is not correctly formatted.
     */
    public static Task databaseParser(String input) throws InvalidInputException{
        String[] splitString = input.split("//");

        int splitStringLength = splitString.length;

        if (splitString[0].equals("todo")) {

            if (splitString.length == 2) {
                return new Todo(String.join(" ", splitString[1]));
            } else if (splitString.length == 3) {
                return new Todo(splitString[1], Boolean.parseBoolean(splitString[2]));
            } else {
                // potential error msg here?
                throw new InvalidInputException("ERROR IN DB");
            }

        } else if (splitString[0].equals("deadline")) {

            if (splitString.length == 4) {
                return new Deadline(splitString[1],
                        splitString[2],
                        splitString[3]);
            } else if (splitString.length == 5) {
                return new Deadline(splitString[1],
                        splitString[2],
                        splitString[3],
                        Boolean.parseBoolean(splitString[4]));
            } else {
                throw new InvalidInputException("ERROR IN DB");
            }

        } else if (splitString[0].equals("event")) {

            if (splitString.length < 4) {
                throw new InvalidInputException("Description of event cannot be empty");
            } else if (splitString.length == 4) {
                return new Event(splitString[1],
                        splitString[2],
                        splitString[3]);
            } else if (splitString.length == 5) {
                return new Event(splitString[1],
                        splitString[2],
                        splitString[3],
                        Boolean.parseBoolean(splitString[4]));
            } else {
                throw new InvalidInputException("ERROR IN DB");
            }
        } else {
            throw new InvalidInputException("Unrecognized task");
        }
    }

    /**
     * Read the database line by line and pass it to databaseParser
     *
     * @param f the file containing the data.
     * @param list the list to store the task.
     * @return TaskList of task after being parsed.
     * @throws FileNotFoundException  If the directory cannot be found/File could not be created.
     */
    public static TaskList readFileContents(File f, TaskList list) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            try {
                list.add(Storage.databaseParser(s.nextLine()));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR IN DATABASE!!!!!!!!!!!!!!!!!!!!!");
            }
        }
        s.close();
        return list;
    }

    /**
     * Writes on to the target file with the current list of Tasks
     *
     * @param f the file to write the data on.
     * @param list the list containing the tasks.
     * @throws IOException  If the file cannot be written on.
     */
    public static void writeToFile(File f, TaskList list) throws IOException {
        FileWriter fw = new FileWriter(f);
        String stringToWrite = "";
        for(int i =0; i < list.size(); i++) {
            Task t = list.get(i);
            stringToWrite = stringToWrite + t.toDataString() + System.lineSeparator();
        }
        fw.write(stringToWrite);
        fw.close();
    }

}
