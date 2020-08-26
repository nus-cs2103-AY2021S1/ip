package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    String filepath;
    File file;

    Storage(String path) {
        this.filepath = path;
        this.file = new File(path);
    }

    boolean create() throws IOException {
        return !file.exists() ? file.createNewFile() : false;
    }

    void printList() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int counter = 1;
        while (s.hasNextLine()) {
            String task = s.nextLine();
            System.out.println(counter + ". " + task);
            counter++;
        }
    }

    int lineCounter() throws FileNotFoundException {
        int counter = 0;
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            counter++;
            s.nextLine();
        }
        return counter;
    }

    String stringMaker(int startIndex, String[] splits, String str) {
         return startIndex >= splits.length - 1
                 ? str
                 : stringMaker(startIndex + 1, splits, splits[startIndex] + " ");
    }

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
                        System.out.println(deadline);
                        arr.add(new Deadline(name, deadline));
                    } else {
                        String[] splits = task.split(" ");
                        String name = splits[1];
                        String deadline = stringMaker(3, splits, "");
                        System.out.println(deadline);
                        arr.add(new Event(name, deadline));
                    }
                }
                return arr;
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    void listWriter(ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task t: arr) {
            String keyword = "";
            String toPrint = "";
            if (t instanceof Deadline) {
                keyword = "by";
                toPrint = " (" + keyword + ": " + t.time + ")";
            } else if (t instanceof Event) {
                keyword = "at";
                toPrint = " (" + keyword + ": " + t.time + ")";
            }
            String text = t.getIndicator() + t.getIcon() + t.name + toPrint + "\n";

            fw.write(text);
        }
        fw.close();
    }

}