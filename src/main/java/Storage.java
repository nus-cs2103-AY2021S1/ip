import java.io.*;
import java.util.ArrayList;

public class Storage {

    private final String path;

    public Storage() {
        this.path = "data/listOfTasks.txt";
    }


    public void load(TaskList tasks) {
        try {
            File file = new File(this.path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] details = line.split(" \\| ");
                boolean isDone = details[1].equals("1") ? true : false;
                switch (details[0]) {
                    case "T":
                        tasks.addTask(new ToDos(details[2], isDone));
                        break;
                    case "D":
                        tasks.addTask(new Deadlines(details[2], details[3], isDone));
                        break;
                    case "E":
                        tasks.addTask(new Events(details[2], details[3], isDone));
                        break;
                }
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println (e);
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(this.path);
            file.getParentFile().mkdirs();
            FileWriter fw;
            if (file.exists()) {
                fw = new FileWriter(file, false);
            } else {
                fw = new FileWriter(file, true);
            }
            for (Task task : tasks) {
                fw.write(task.writeToFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println (e);
        }
    }
}
