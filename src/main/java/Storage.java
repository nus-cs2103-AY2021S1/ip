import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final TaskList taskList;
    private final String pathname;
    private final File file;

    public Storage(TaskList taskList, String name) {
        this.taskList = taskList;
        pathname = Storage.getStoragePath(name);
        file = new File(pathname);
    }

    public void fileCheck() {
        try {
            File f = new File(pathname);
            f.getParentFile().mkdirs();

            if (!f.createNewFile()) {
                loadFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong! Backup could not be made.");
        }
    }

    public void saveFile() {
        try {
            FileWriter fw = new FileWriter(file);
            List<Task> storageCopy = this.taskList.getList();
            String split = "_";
            int index = 0;

            while (!storageCopy.isEmpty()) {
                Task curr = storageCopy.remove(index);
                String currString = curr.toString();
                String type = currString.substring(currString.indexOf("[") + 1, currString.indexOf("]"));
                boolean completed = curr.isDone();
                String name = curr.getName();

                fw.write(type + split);
                fw.write(completed + split);
                fw.write(name + split);

                switch (type) {
                    case "E":
                        Event event = (Event) curr;
                        fw.write(event.getAt() + System.lineSeparator());
                        break;
                    case "D":
                        Deadline deadline = (Deadline) curr;
                        fw.write(deadline.getBy() + System.lineSeparator());
                        break;
                    default:
                        fw.write("mark" + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops. Something went wrong while saving data.");
        }

    }

    public  void loadFile() {
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String curr = scan.nextLine();
                String[] data = curr.split("_");
                String type = data[0];
                boolean done = Boolean.parseBoolean(data[1]);
                String name = data[2];
                String time = null;
                if (!data[3].equals("mark")) {
                    time = data[3];
                }

                switch (type) {
                    case "E":
                        taskList.getList().add(new Event(name, done, time));
                        break;
                    case "D":
                        taskList.getList().add(new Deadline(name, done, time));
                        break;
                    default:
                        taskList.getList().add(new ToDo(name, done));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void updateFile() {
        file.delete();
        saveFile();
        loadFile();
    }

    public static String getStoragePath(String name) {
        return System.getProperty("user.dir")
                + File.separator + "data" + File.separator + name + ".txt";
    }

    public File getFile() {
        return file;
    }
}
