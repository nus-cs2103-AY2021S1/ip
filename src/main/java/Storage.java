import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> list;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    private void createTask(String s) {
        Task next;
        if (s.startsWith("todo")) {
            next = new ToDo(s.substring(5));
        } else if (s.startsWith("deadline")) {
            String pattern = "(deadline\\s)(.+)\\s(/by\\s)(.+)";
            String task = s.replaceAll(pattern, "$2");
            String time = s.replaceAll(pattern, "$4");
            next = new Deadline(task, time);
        } else {
            String pattern = "(event\\s)(.+)\\s(/at\\s)(.+)";
            String task = s.replaceAll(pattern, "$2");
            String time = s.replaceAll(pattern, "$4");
            next = new Event(task, time);
        }
        list.add(next);
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            Scanner listScanner = new Scanner(f);
            int index = 0;
            while (listScanner.hasNext()) {
                String command = listScanner.nextLine();
                if (command.startsWith("done")) {
                    createTask(command.substring(5));
                    list.get(index).setDone();
                } else {
                    createTask(command);
                }
                index++;
            }
            return list;
        } catch (FileNotFoundException e) {
            throw DukeException.loadingError(filePath);
        }
    }

    public void save(TaskList tasks) throws DukeException {
        File saveFile = new File(filePath);
        File parent_directory = saveFile.getParentFile();

        if (null != parent_directory) {
            parent_directory.mkdirs();
        }

        try {
            FileWriter fw = new FileWriter(saveFile);
            String data = tasks.extractListData();
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw DukeException.storageIOException(e.getMessage());
        }


    }
}
