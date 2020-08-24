import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() {
        ArrayList<Task> arr = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            Scanner sc = new Scanner(path);
            while(sc.hasNextLine()) {
                String[] parts = sc.nextLine().split("//");
                switch (parts[0]) {
                case "T":
                    arr.add(new ToDo(parts[2], parts[1].equals("1")));
                    break;
                case "E":
                    arr.add(new Event(parts[2], parts[1].equals("1"), parts[3]));
                    break;
                case "D":
                    arr.add(new Deadline(parts[2], parts[1].equals("1"), parts[3]));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    void save(ArrayList<Task> arr) {
            File file = new File(filePath);
            try {
                FileWriter writer = new FileWriter(file);
                writer.close();

                for (Task t : arr) {
                    FileWriter fileWriter = new FileWriter(file, true);
                    fileWriter.write(t.writeToFile());
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
}
