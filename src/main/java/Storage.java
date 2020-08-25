
//import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

public class Storage {

    // private File file;
    private Path path;
    private Path dir;
    private File file;

    public Storage() {
        // file = new File("../data/list.txt");
        path = Paths.get("data/");
        try {
            dir = Files.createDirectories(path);
            // Files.createFile(dir);
            file = new File("data/list.txt");
            file.createNewFile();
            // System.out.println("path created");
        } catch (FileAlreadyExistsException e) {
            //System.out.println("already exists");
            // the directory already exists.
        } catch (IOException e) {
            System.out.println("ioexception");
            // something else went wrong
        }

    }

    private String format(Task t) {
        String div = "-----";
        String timing = t.getTiming();
        if (t.getType().equals("T")) {
            timing = "NaN";
        }
        return t.getType() + div +
            t.getStatusIcon() + div
            + t.getDescription() +
            div + timing; 
    }

    private Task reformat(String s) {
        String[] arr = s.split("-----");
        Task t = null;
        // System.out.println(s);
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[i] + " is " + i + "th index of split");
        // }
        switch (arr[0]) {
        
        case "T":
            t = new Todo("todo " + arr[2]);
            break;
        case "E":
            t = new Event(arr[2], arr[3]);
            break;
        case "D":
            t = new Deadline(arr[2], arr[3]);
            break;
        default:
            System.out.println("Something went wrong here");
        }
        if (arr[1].equals("\u2713")) {
            t.markAsDone();
        }
        return t;
    }

    public void updateList(TaskList p) {

        try {
            FileWriter fw = new FileWriter(this.file.getPath());
            for (Task t: p.getList()) {
                fw.write(this.format(t) + "\n");
            }
            fw.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadList(TaskList p) {
        try {
            FileReader fr = new FileReader(this.file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String currLine = br.readLine();
            while (currLine != null) {
                // System.out.println(currLine);
                p.addTask(this.reformat(currLine));
                currLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}