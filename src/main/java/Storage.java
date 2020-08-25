import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public ArrayList<Task> load() {
        try {
            String root = System.getProperty("user.dir");
            File f = this.checkAndCreateFile(root);
            ArrayList<Task> list = new ArrayList<>();
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String[] next = scanner.nextLine().split("/");
                Task task = null;
                switch (next[0]) {
                case "T":
                    task = new Todo(next[2]);
                    break;
                case "D":
                    task = new Deadline(next[2], next[3]);
                    break;
                case "E":
                    task = new Event(next[2], next[3]);
                    break;
                }
                if (next[1].equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException exc) {
            System.out.println(exc.getMessage());
            return new ArrayList<>();
        }

    }

    public File checkAndCreateFile(String root) {
        try {
            boolean directoryExists = Files.exists(Paths.get(root, "data"));
            boolean fileExists = Files.exists(Paths.get(root, "data", "dukeTaskList.txt"));
            if (!directoryExists) {
                Files.createDirectory(Paths.get(root, "data"));
            }
            if (!fileExists) {
                Files.createFile(Paths.get(root, "data", "dukeTaskList.txt"));
            }
            return new File(Paths.get(root, "data", "dukeTaskList.txt").toString());
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    public void saveTasks() throws IOException {
        String root = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(Paths.get(root, "data", "dukeTaskList.txt").toString());
        for (int i = 0; i < TaskList.list.size(); i++) {
            Task task = TaskList.list.get(i);
            String toAdd = task.taskSaver();
            fw.write(toAdd + "\n");
        }
        fw.close();
    }

}
