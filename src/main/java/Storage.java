import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    public List<Task> load() {
        if (!this.taskFile.exists()) {
            File dir = this.taskFile.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            try {
                this.taskFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace(); // todo
            }
        }

        Scanner sc = null;
        try {
            sc = new Scanner(this.taskFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // todo
        }

        if (sc != null) {
            List<Task> list = new ArrayList<>();
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
            return list;
        } else {
            return new ArrayList<Task>(); //todo
        }
    }

    public void update(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile, true);
            fileWriter.write(task.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(List<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile);
            String fileContents = list.get(0).toString();

            for (int i = 1; i < list.size(); i++) {
                fileContents += "\n" + list.get(i).toString();
            }

            fileWriter.write(fileContents);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace(); // todo
        }
    }
}
