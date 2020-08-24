import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
    private String currentDir = System.getProperty("user.dir");
    private Path dataDir = Paths.get(currentDir, "src", "main", "data");
    private Path fileDir = Paths.get(currentDir, "src", "main", "data", "list.txt");

    private static final Pattern pattern = Pattern.compile("\\[([TDE])]\\s\\[(1|0)]\\s(.*?)\\s?/(?:.*?\\s(.*+))?");

    private boolean dirExists() {
        return Files.exists(dataDir);
    }

    private boolean fileExists() {
        return Files.exists(fileDir);
    }

    private File createFile() {
        File newFile = new File(dataDir.toString(), "list.txt");
        System.out.println("Creating File...");
        try {
            newFile.createNewFile();
            return newFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getFile() {
        if (fileExists()) {
            return fileDir.toFile();
        } else if (dirExists()) {
            return createFile();
        } else {
            dataDir.toFile().mkdirs();
            return createFile();
        }
    }

    public boolean updateFile(DukeList list) {
        try {
            System.out.println("Saving changes...");
            File file = getFile();
            FileWriter writer = new FileWriter(file);
            for (Task t : list.getList()) {
                writer.write(t.getSaveString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Changes saved.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public DukeList getList() {
        try {
            DukeList list = new DukeList();
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                Matcher matcher = pattern.matcher(scanner.nextLine());
                if (matcher.find()) {
                    boolean done = matcher.group(2).equals("1");
                    String task = matcher.group(3);
                    String date = matcher.group(4);
                    LocalDate localDate = null;

                    if (date != null && !date.equals("null")) {
                        localDate = LocalDate.parse(date);
                    }
                    switch (matcher.group(1)) {
                        case ("T"):
                            list.addItem(new Todo(task, done));
                            break;
                        case ("D"):
                            list.addItem(new Deadline(task, done, localDate));
                            break;
                        case ("E"):
                            list.addItem(new Event(task, done, localDate));
                            break;
                    }
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return new DukeList();
        }
    }
}
