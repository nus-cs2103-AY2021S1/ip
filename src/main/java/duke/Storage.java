package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private File file;

    Storage(String path) {
        this.filepath = path;
        this.file = new File(filepath);
    }

    /**
     * Creates a new file if it does not exist.
     * @return boolean
     * @throws IOException if file not found
     */
    boolean create() throws IOException {
        return !file.exists() && file.createNewFile();
    }

    /**
     * Counts the number of lines in the hard disk.
     * @return int
     * @throws FileNotFoundException if file is not found
     */
    int lineCounter() throws FileNotFoundException {
        int counter = 0;
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            counter++;
            s.nextLine();
        }
        return counter;
    }

    /**
     * Makes a string from the startIndex to the end of an array of strings.
     * @param startIndex starting index.
     * @param splits array of strings.
     * @param str accumulation of the string.
     * @return time of deadline string
     */
    String stringMaker(int startIndex, String[] splits, String str) {
        String word = splits[startIndex];
        return startIndex > splits.length - 1
                 ? str
                 : stringMaker(startIndex + 1, splits, (str.length() > 0 ? str + " " + word : str + word));
    }

    /**
     * Reads the hard disk and converts it into an arraylist.
     * @return ArrayList(Task) the arraylist representation of the hard disk
     */
    ArrayList<Task> toArrayList() {
        try {
            ArrayList<Task> arr = new ArrayList<>();
            if (lineCounter() == 0) {
                return arr;
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String task = s.nextLine();
                    String[] splits = task.split(" ");
                    String name = splits[1];
                    char icon = splits[0].charAt(4);
                    if (task.charAt(1) == 'T') {
                        Todo t = new Todo(name);
                        if (icon == '1') {
                            t.taskIsDone();
                        } else {
                            t.taskIsNotDone();
                        }
                        arr.add(t);
                    } else if (task.charAt(1) == 'D') {
                        String deadline = stringMaker(3, splits, "");
                        Deadline d = new Deadline(name, deadline.substring(0, deadline.length() - 1));
                        if (icon == '1') {
                            d.taskIsDone();
                        } else {
                            d.taskIsNotDone();
                        }
                        arr.add(d);
                    } else {
                        String deadline = stringMaker(3, splits, "");
                        Event e = new Event(name, deadline.substring(0, deadline.length() - 1));
                        if (icon == '1') {
                            e.taskIsDone();
                        } else {
                            e.taskIsNotDone();
                        }
                        arr.add(e);
                    }
                }
                return arr;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Updates the hard disk based on the provided arraylist.
     * @param arr the arraylist that is being copied to the hard disk.
     * @throws IOException if FileWriter fails
     */
    void listWriter(ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task t : arr) {
            String keyword;
            String toPrint = "";
            if (t instanceof Deadline) {
                keyword = "by";
                toPrint = " (" + keyword + ": " + t.getTime() + ")";
            } else if (t instanceof Event) {
                keyword = "at";
                toPrint = " (" + keyword + ": " + t.getTime() + ")";
            }
            String indicator = t.getIndicator();
            String icon = t.getIcon();
            assert (indicator.equals("[T]") || indicator.equals("[D]") || indicator.equals("[E]"));
            String text = indicator + (icon.equals("[" + "\u2713" + "] ") ? "[1] " : "[0] ")
                    + t.getName() + toPrint + "\n";

            fw.write(text);
        }
        fw.close();
    }
}
