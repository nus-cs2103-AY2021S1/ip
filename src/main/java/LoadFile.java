import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class LoadFile {
    private Echo loadTasks(String filePath) throws DukeException {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            Echo echo = new Echo();

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] arr = data.split("\\|");
                String task = arr[0];
                String doneStatus = arr[1];
                boolean isDone = doneStatus.equals("1");
                String description = arr[2];
                String timing = arr.length == 4 ? arr[3] : "";
                Task t;

                switch (task) {
                case "T":
                    t = new ToDo(description);
                    break;
                case "E":
                    t = new Event(description, timing);
                    break;
                case "D":
                    t = new Deadline(description, timing);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, "
                            +"but I don't recognise the command from the text file.");
                }

                if (isDone) {
                    t.markAsDone();
                }

                echo.addTask(t);
            }

            return echo;
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

        return new Echo();
    }

    public Echo loadFile() {
        String src = System.getProperty("user.dir");
        Path path = Paths.get(src, "data", "duke.txt");
        boolean dirExists = Files.exists(path);

        if (dirExists) {
            try {
                return loadTasks(path.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            try {
                File file = new File(path.toString());
                file.getParentFile().mkdir();
                file.createNewFile();
                return new Echo();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        return new Echo();
    }
}
