import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Storage {
    final String FILE_PATH = "../tasks.txt";

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        
        try {
            File f = new File(FILE_PATH);
            Scanner sc = new Scanner(f);
            
            while (sc.hasNext()) {
                String taskInfo = sc.nextLine();
                String[] arr = taskInfo.split(",");
                switch (arr[0]) {
                    case "T":
                        Todo t = new Todo(arr[2]);
                        if (arr[1].equals("1")) t.markDone();
                        tasks.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(arr[2], arr[3]);
                        if (arr[1].equals("1")) d.markDone();
                        tasks.add(d);
                        break;
                    case "E":
                        Event e = new Event(arr[2], arr[3], arr[4]);
                        if (arr[1].equals("1")) e.markDone();
                        tasks.add(e);
                        break;
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            // TODO: Customise font
            System.out.println("File \"tasks.txt\" does not exist. Attempting to create one for you.");
            try {
                FileWriter fw = new FileWriter(FILE_PATH);
                fw.close();
                System.out.println("Successfully created file tasks.txt");
                return tasks;
            } catch (IOException ioException) {
                throw new DukeException(ioException.getMessage());
            }
        }
    }
}