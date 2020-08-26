import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskData {
    private ArrayList<Task>  tasks;
    private static String filePath = "data/duke.txt";

    public TaskData() {
        createFileIfAbsent();
        try {
            tasks = retrieveTasks();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        }

    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public static void createFileIfAbsent() {
        File f = new File(filePath);

        if (!f.exists()) {
            try {
            File dir = new File ("data");
            dir.mkdir();
            f.createNewFile();
            } catch (IOException e) {
                System.out.println("File unable to be created");
            }
        }
    }

    public static ArrayList<Task> retrieveTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArr = line.split(" ; ");
            Task t;

            if (lineArr[0].equals("T")) {
                t = new Todo(lineArr[2]);
            } else if (lineArr[0].equals("E")) {
                t = new Event(lineArr[2], lineArr[3]);
            } else {
                t = new Deadline(lineArr[2], lineArr[3]);
            }

            if (lineArr[1].equals("1")) {
                t.markDone();
            }

            tasks.add(t);
        }

        return tasks;
    }

    public void updateData() {
        try {
            String data = "";

            for (int i = 0; i < tasks.size(); i++) {
                if (i == tasks.size() - 1) {
                    data += tasks.get(i).toTaskData();
                } else {
                    data += tasks.get(i).toTaskData() + "\n";
                }
            }

            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to update data");
        }

    }
}
