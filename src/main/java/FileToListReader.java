import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToListReader {
    private final List<Task> list = new ArrayList<>();

    public FileToListReader(File taskfile) throws FileNotFoundException {
        Scanner sc = new Scanner(taskfile);
        while (sc.hasNext()) {
            String listItem = sc.nextLine();
            String[] words = listItem.split(" ", 2);

            if (listItem.charAt(1) == 'T') {
                list.add(new Todo(words[1]));
            } else if (listItem.charAt(1) == 'D') {
                String taskString = words[1];
                String[] temp = taskString.split(" by: ");
                list.add(new Deadline(temp[0], temp[1]));
            } else if (listItem.charAt(1) == 'E') {
                String taskString = words[1];
                String[] temp = taskString.split(" at: ");
                list.add(new Event(temp[0], temp[1]));
            }
        }
    }

    public List getList() {
        return this.list;
    }
}
