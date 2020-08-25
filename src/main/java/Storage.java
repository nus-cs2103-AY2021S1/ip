import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<String> load() throws IOException, DukeException {
        boolean isFound = createFile();
        if (!isFound) {
            throw new DukeException();
        }
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lst = new ArrayList<>();
        while (scanner.hasNext()) {
            lst.add(scanner.nextLine());
        }
        scanner.close();
        return lst;
    }

    public boolean createFile() throws IOException {
        boolean isFound = true;
        File file;
        file = new File(filepath.substring(0, filepath.lastIndexOf("/")));
        if (!file.isDirectory()) {
            isFound = false;
            file.mkdirs();

        }
        file = new File(filepath);
        if (file.createNewFile()) {
            isFound = false;
        }
        return isFound;
    }

    public void save(ArrayList<Task> lst) throws IOException {
        File file = new File(filepath);
        new FileWriter(file, false).close();
        FileWriter filewriter = new FileWriter(file, true);
        for (Task task : lst) {
            filewriter.write(task.getFormattedString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
