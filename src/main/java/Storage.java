//package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * store data file -> load, save
 */
public class Storage {

    public Storage() {

    }

    /**
     * read data from file data/duke.txt
     */
    public static ArrayList<Task> readData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File myObj = new File("data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            if(myReader.hasNextInt()) {
                int n = Integer.parseInt(myReader.nextLine());
                for (int i = 1; i <= n; ++i) {
                    String str = myReader.nextLine();
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add("");
                    for(int j = 0; j < str.length(); ++j) {
                        if(str.charAt(j) == '|') { arr.add(""); continue; }
                        arr.set(arr.size() - 1, arr.get(arr.size() - 1) + str.charAt(j));
                    }
                    if (arr.get(0).equals("T")) {
                        tasks.add(new Todo(arr.get(1)));
                        tasks.get(i - 1).isDone = arr.get(2).equals("true");
                    } else if (arr.get(0).equals("D")) {
                        tasks.add(new Deadline(arr.get(1), arr.get(2)));
                        tasks.get(i - 1).isDone = arr.get(3).equals("true");
                    } else if (arr.get(0).equals("E")) {
                        tasks.add(new Event(arr.get(1), arr.get(2)));
                        tasks.get(i - 1).isDone = arr.get(3).equals("true");
                    }
                }
                myReader.close();
            }
        } catch (FileNotFoundException e) {
        }
        return tasks;
    }

    /**
     * save to data file
     */
    public static void updateDataFile(ArrayList<Task> tasks) {
        try {
            File myObj = new File("data");
            myObj.mkdir();
            myObj = new File("data/duke.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter("data/duke.txt");
            myWriter.write(listToDataString(tasks));
            myWriter.close();
        } catch (IOException e) {
        }
    }

    /**
     * data format
     */
    public static String listToDataString(ArrayList<Task> tasks) {
        String res = "";
        res += tasks.size() + "\n";
        for(int i = 1; i <= tasks.size(); ++i) {
            res += tasks.get(i - 1).getType() + "|" + tasks.get(i - 1).getDescription() + "|" + tasks.get(i - 1).isDone + "\n";
        }
        return res;
    }
}
