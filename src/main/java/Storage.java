package duke;
import java.util.*;
import java.io.*;

public class Storage {
    File file;

    Storage(String filename) {
        try {
            file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred when interacting with file.");
            e.printStackTrace();
        }
    }

    public List<Task> loadFile() {
        List<Task> lst = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            String type, desc, by, at;
            int status;
            while (sc.hasNext()) {
                type = sc.nextLine();
                desc = sc.nextLine();
                status = Integer.parseInt(sc.nextLine());
                if (type.equals("todo"))
                    lst.add(new Todo(desc));
                else if (type.equals("deadline")) {
                    by = sc.nextLine();
                    lst.add(new Deadline(desc, by));
                } else if (type.equals("event")) {
                    at = sc.nextLine();
                    lst.add(new Event(desc, at));
                }
                if (status == 1) lst.get(lst.size() - 1).markDone();
            }
        } catch (IOException e) {
            System.out.println("An error occurred when interacting with file.");
            e.printStackTrace();
        }
        return lst;
    }

    public void writeFile(List<Task> lst) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : lst) {
                writer.write(task.toDisk() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when write to file");
            e.printStackTrace();
        }

    }
}