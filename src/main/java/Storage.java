import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;



/**
 * Encapsulates a Storage
 * Deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {
    protected String fileName;

    /**
     * Constructor
     *
     * @param name is the filename that data is going to be stored in
     */
    public Storage(String name) {
        this.fileName = name;
    }

    /**
     * Initialises the file for data to be stored in
     */
    protected void init() {
        Class myClass = getClass();
        URL url = myClass.getResource("Data.txt");
        if (url == null) {
            File file = new File("Data.txt");
        }
    }

    /**
     * Imports the data from the data file into a list
     *
     * @param list is the list that the data is going to be stored in
     */
    protected void importSavedDataToList(ArrayList<Task> list) {
        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader("Data.txt"));
            while ((strCurrentLine = objReader.readLine()) != null) {
                String taskIcon = Character.toString(strCurrentLine.charAt(0));
                String doneIcon = Character.toString(strCurrentLine.charAt(4));
                boolean isDone = false;
                if (doneIcon.equals("1")) {
                    isDone = true;
                }
                if (taskIcon.equals("T")) {
                    String description = strCurrentLine.substring(8);
                    Todo task = new Todo(description, isDone);
                    list.add(task);
                } else if (taskIcon.equals("D")) {
                    int indexOfLastDivider = strCurrentLine.lastIndexOf("|");
                    String description = strCurrentLine.substring(8, indexOfLastDivider - 1);
                    String by = strCurrentLine.substring(indexOfLastDivider + 2);
                    LocalDate date = LocalDate.parse(by);
                    Deadline task = new Deadline(description, isDone, date);
                    list.add(task);
                } else if (taskIcon.equals("E")) {
                    int indexOfLastDivider = strCurrentLine.lastIndexOf("|");
                    String description = strCurrentLine.substring(8, indexOfLastDivider - 1);
                    String at = strCurrentLine.substring(indexOfLastDivider + 2);
                    Event task = new Event(description, isDone, at);
                    list.add(task);
                } else {
                    System.out.println("Invalid first Character");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Clears all data in the data file
     */
    protected void clearData() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }

    /**
     * Uploads data from the list onto the data file
     *
     * @param list is the list that the data is going to be uploaded from
     */
    protected void saveListToData(ArrayList<Task> list) {
        this.clearData();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String input = "";
            if (task instanceof Todo) {
                if (task.isDone) {
                    input = "T | 1 | " + task.description;
                } else {
                    input = "T | 0 | " + task.description;
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isDone) {
                    input = "D | 1 | " + deadline.description + " | " + deadline.by;
                } else {
                    input = "D | 0 | " + deadline.description + " | " + deadline.by;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isDone) {
                    input = "E | 1 | " + event.description + " | " + event.at;
                } else {
                    input = "E | 0 | " + event.description + " | " + event.at;
                }
            } else {

            }
            try {
                FileWriter writer = new FileWriter("Data.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(input);
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
