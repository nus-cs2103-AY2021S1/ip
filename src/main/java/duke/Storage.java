package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Storage {
    private String filepath;
    private File file;
    private Stack<String> actions;

    Storage(String path) {
        this.filepath = path;
        this.file = new File(filepath);
        this.actions = new Stack<>();
    }

    /**
     * Creates a new file if it does not exist.
     * @return boolean
     * @throws IOException
     */
    boolean create() throws IOException {
        return !file.exists() && file.createNewFile();
    }

    /**
     * Counts the number of lines in the hard disk.
     * @return int
     * @throws FileNotFoundException
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
     * @return
     */
    String stringMaker(int startIndex, String[] splits, String str) {
        return startIndex >= splits.length - 1
                 ? str
                 : stringMaker(startIndex + 1, splits, splits[startIndex] + " ");
    }

    /**
     * Reads the hard disk and converts it into an arraylist.
     * @return ArrayList(Task) the arraylist representation of the hard disk
     * @throws Exception
     */
    ArrayList<Task> toArrayList() throws Exception {
        try {
            ArrayList<Task> arr = new ArrayList<>();
            if (lineCounter() == 0) {
                return arr;
            } else {
                int counter = 0;
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String task = s.nextLine();
                    if (task.charAt(1) == 'T') {
                        arr.add(new Todo(task.substring(7)));
                    } else if (task.charAt(1) == 'D') {
                        String[] splits = task.split(" ");
                        String name = splits[1];
                        String deadline = stringMaker(3, splits, "");
                        arr.add(new Deadline(name, deadline));
                    } else {
                        String[] splits = task.split(" ");
                        String name = splits[1];
                        String deadline = stringMaker(3, splits, "");
                        arr.add(new Event(name, deadline));
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
     * @throws IOException
     */
    void listWriter(ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task t : arr) {
            String keyword = "";
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
            assert (indicator.equals("[T]") || indicator.equals("[D]") || indicator.equals("[E]"))
                    && (icon.equals("[" + "\u2713" + "] ") || icon.equals("[" + "\u2718" + "] "));
            String text = indicator + icon + t.getName() + toPrint + "\n";

            fw.write(text);
        }
        fw.close();
    }
}
