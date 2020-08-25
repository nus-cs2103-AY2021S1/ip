import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    ArrayList<Task> al;
    String filePath;

    protected Storage(String filePath) {
        al = new ArrayList<>();
        this.filePath = filePath;
    }

    protected ArrayList<Task> load() {
        try {
            String[] path = filePath.split("/", 2);
            java.nio.file.Path dataPath = java.nio.file.Paths.get(".", path[0]);
            java.nio.file.Path dukePath = java.nio.file.Paths.get(".", path[0], path[1]);

            File dataFile = new File(String.valueOf(dataPath));
            File duke = new File(String.valueOf(dukePath));

            if (dataFile.exists()) {
                if (duke.exists()) {
                    FileReader fr = new FileReader(duke);
                    BufferedReader br = new BufferedReader(fr);
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null) {
                        String temp[] = line.split(" // ", 4);
                        addTaskToArrayList(temp, al);
                    }
                    fr.close();
                } else {
                    duke.createNewFile();
                }
            } else {
                //create data file
                dataFile.mkdir();
                duke.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return al;
    }

    private static void addTaskToArrayList(String[] temp, ArrayList<Task> al) {
        boolean isDone = Integer.parseInt(temp[1]) == 0;
        switch (temp[0]) {
            case "T":
                Todo td = new Todo(temp[2], isDone);
                al.add(td);
                break;
            case "D":
                LocalDate dldate = LocalDate.parse(temp[3]);
                Deadline dl = new Deadline(temp[2], isDone, dldate);
                al.add(dl);
                break;
            case "E":
                LocalDate edate = LocalDate.parse(temp[3]);
                Event e = new Event(temp[2], isDone, edate);
                al.add(e);
                break;
        }
    }

    protected void upload(TaskList tasks) throws YooException {
        File duke = new File(filePath);

        try {
            FileWriter fw = new FileWriter(duke, false);

            for (Task t : al) {
                int isDone = t.isDone ? 1 : 0;

                switch (t.getClass().getName()) {
                    case "Todo":
                        fw.write("T // " + isDone + " // " + t.description + " // \n");
                        break;
                    case "Deadline":
                        Deadline dl = (Deadline) t;
                        fw.write("D // " + isDone + " // " + dl.description + " // " + dl.by + "\n");
                        break;
                    case "Event":
                        Event e = (Event) t;
                        fw.write("E // " + isDone + " // " + e.description + " // " + e.at + "\n");
                        break;
                }
            }
            fw.close();
        } catch (IOException e){
            throw new YooException("File not found!");
        }
    }
}
