import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Storage {
    private final String HOME = System.getProperty("user.home");
    private final Path DIR = Paths.get(HOME, "data");
    private final Path PATH = Paths.get(HOME, "data", "iPStore.txt");
    private final String IOMESSAGE = "Sorry! There was an IOException! Initialising with an empty Tasklist!";
    private final String INVALIDMESSAGE = "Sorry! There was an error in reading your data! Initialising with a semi-complete Tasklist!";

    public Storage() {}

    private String allTasksCombined(ArrayList<Task> items) {
        String res = "";
        for (Task item : items) {
            res += String.format("%s", item.saveString());
            res += "\n";
        }
        return res;
    }

    private Task stringToTask(String str) throws InvalidTypeException, InvalidDataException {
        String[] info = str.split("\\|");
        if (info.length < 3) {
            throw new InvalidDataException();
        }
        Boolean isComplete = info[1].equals("1");
        switch (info[0]) {
            case "T":
                return new Todo(info[2], isComplete);
            case "D":
            case "E":
                return new Event(info[2], isComplete, info[3]);
            default:
                throw new InvalidTypeException();
        }
    }

    public void writeData(ArrayList<Task> items) throws IOException {
        Boolean directoryExists = Files.exists(DIR);

        if (!directoryExists) {
            Files.createDirectory(DIR);
        }

        try {
            FileWriter fw = new FileWriter(PATH.toFile());
            PrintWriter pw = new PrintWriter(fw);

            pw.print(allTasksCombined(items));

            pw.close();
        } catch (IOException e){
            throw e;
        }
    }

    public ArrayList<Task> readData() {
        Boolean pathExists = Files.exists(PATH);

        ArrayList<Task> res = new ArrayList<>();

        if (pathExists) {
            try {
                FileReader fr = new FileReader(PATH.toFile());
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    res.add(stringToTask(str));
                }
            } catch (IOException e) {
                e.printStackTrace();
                Ui.addLine(this.IOMESSAGE);
            } catch (InvalidTypeException e) {
                System.out.println(e.toString());
                Ui.addLine(this.INVALIDMESSAGE);
            } catch (InvalidDataException e) {
                System.out.println(e.toString());
                Ui.addLine(this.INVALIDMESSAGE);
            }
        }

        return res;
    }
}