package duke.storage;

import duke.task.*;
import duke.utility.MyString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class controls of storing and loading data.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor.
     * @param filePath Path where data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Translate data into a easily-read format
     * @param str Unformatted data used for user's readability
     * @return list of string represent the data
     */
    private ArrayList<String> parseData(String str) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("");
        for (int j = 0; j < str.length(); ++j) {
            if (str.charAt(j) == '|') { arr.add(""); continue; }
            arr.set(arr.size() - 1, arr.get(arr.size() - 1) + str.charAt(j));
        }
        return arr;
    }

    /**
     * Load previous task from the stored file
     * @return List of tasks
     */
    public ArrayList<Task> readData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            int n = 0;
            if(myReader.hasNextInt()) n = Integer.parseInt(myReader.nextLine());
            for (int i = 1; i <= n; ++i) {
                String str = myReader.nextLine();
                ArrayList<String> arr = parseData(str);
                switch (arr.get(0)) {
                    case "T":
                        addToDo(tasks, arr);
                        break;
                    case "D":
                        addDeadline(tasks, arr);
                        break;
                    case "E":
                        addEvent(tasks, arr);
                        break;
                    case "W":
                        addDoWithin(tasks, arr);
                        break;
                    default:
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            return tasks;
        }
        return tasks;
    }

    /**
     * Load todo
     * @param tasks current list
     * @param arr formatted data
     */
    private void addToDo(ArrayList<Task> tasks, ArrayList<String> arr) {
        String description = arr.get(1);
        boolean isDone = arr.get(2).equals("true");
        tasks.add(new Todo(description, isDone));
    }

    /**
     * Load deadline
     * @param tasks current list
     * @param arr formatted data
     */
    private void addDeadline(ArrayList<Task> tasks, ArrayList<String> arr) {
        String description = arr.get(1);
        boolean isDone = arr.get(2).equals("true");
        String deadline = arr.get(3);
        tasks.add(new Deadline(description, isDone, deadline));
    }

    /**
     * Load event
     * @param tasks current list
     * @param arr formatted data
     */
    private void addEvent(ArrayList<Task> tasks, ArrayList<String> arr) {
        String description = arr.get(1);
        boolean isDone = arr.get(2).equals("true");
        String getTime = arr.get(3);
        tasks.add(new Event(description, isDone, getTime));
    }

    /**
     * Load dowithin
     * @param tasks current list
     * @param arr formatted data
     */
    private void addDoWithin(ArrayList<Task> tasks, ArrayList<String> arr) {
        String description = arr.get(1);
        boolean isDone = arr.get(2).equals("true");
        String from = arr.get(3);
        String to = arr.get(4);
        tasks.add(new DoWithinPeriodTasks(description, isDone, from, to));
    }

    /**
     * Overwrite current data file.
     * @param tasks new tasks that need to be updated.
     */
    public void updateDataFile(ArrayList<Task> tasks) {
        try {
            File myObj = new File("data");
            myObj.mkdir();
            myObj = new File(filePath);
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(listToDataString(tasks));
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * translate data into formatted version to be stored.
     * @param tasks tasks list
     * @return String represent the new format.
     */
    public static String listToDataString(ArrayList<Task> tasks) {
        MyString res = new MyString();
        res.addNewLines(Integer.toString(tasks.size()));
        for (int i = 1; i <= tasks.size(); ++i) {
            res.addNewLines(tasks.get(i - 1).getDataFormat());
        }
        //tasks.forEach((n) -> { res += n.getType() + "|" + n).getDescription() + "|" + tasks.get(i - 1).isDone + "\n"});
        return res.toString();
    }
}
