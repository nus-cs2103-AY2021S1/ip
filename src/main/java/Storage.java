import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        createFileIfAbsent(filePath);
    }

    public static void createFileIfAbsent(String filePath) {
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

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("Saved list of tasks could not be loaded");
        }

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

    public void updateData(TaskList tasks) {
        try {
            String data = "";

            for (int i = 1; i <= Task.totalTasks; i++) {
                if (i == Task.totalTasks) {
                    data += tasks.getTask(i).toTaskData();
                } else {
                    data += tasks.getTask(i).toTaskData() + "\n";
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
