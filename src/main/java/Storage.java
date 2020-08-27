import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        createFileIfAbsent(filePath);
    }

    public static void createFileIfAbsent(String filePath) throws DukeException {
        File f = new File(filePath);

        if (!f.exists()) {
            try {
                File dir = new File ("data");
                dir.mkdir();
                f.createNewFile();
            } catch (IOException ex) {
                throw new DukeException("File to store tasks could not be created!"
                        + "\n" + "Your tasks won't be saved upon exit :(");
            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException ex) {
            throw new DukeException("Saved list of tasks could not be loaded");
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] taskInfos = line.split(" ; ");
            Task t;

            if (taskInfos[0].equals("T")) {
                t = new Todo(taskInfos[2]);
            } else if (taskInfos[0].equals("E")) {
                t = new Event(taskInfos[2], taskInfos[3]);
            } else {
                t = new Deadline(taskInfos[2], taskInfos[3]);
            }

            if (taskInfos[1].equals("1")) {
                t.markDone();
            }

            tasks.add(t);
        }

        return tasks;
    }

    public void updateData(TaskList tasks) throws DukeException {
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
        } catch (IOException ex) {
            throw new DukeException("Saved list of tasks could not be updated");
        }

    }
}
