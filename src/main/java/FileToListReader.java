import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToListReader {
    private final List<Task> list = new ArrayList<>();

    public FileToListReader(File taskfile) throws FileNotFoundException, WrongDeadlineException {
        Scanner sc = new Scanner(taskfile);
        while (sc.hasNext()) {
            String listItem = sc.nextLine();
            String[] words = listItem.split(" ", 2);

            if (listItem.charAt(1) == 'T') {
                list.add(new Todo(words[1]));
            } else if (listItem.charAt(1) == 'D') {
                String taskString = words[1];
                String[] temp = taskString.split(" by: ");
                LocalDateTime dateTime = LocalDateTime.parse(temp[1],
                        DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
                list.add(new Deadline(temp[0], dateTime));
            } else if (listItem.charAt(1) == 'E') {
                String taskString = words[1];
                String[] temp = taskString.split(" at: ");
                LocalDateTime dateTime = LocalDateTime.parse(temp[1],
                        DateTimeFormatter.ofPattern("d MMM yyyy, h.m a"));
                list.add(new Deadline(temp[0], dateTime));
            }
        }
    }

    public List getList() {
        return this.list;
    }
}
