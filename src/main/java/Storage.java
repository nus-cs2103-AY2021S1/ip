import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Saves current Task list onto a text file
    public void saveData(ArrayList<Task> taskList) {

        try {
            File file = new File(this.filePath);

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, false);

            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    ToDo currentTask = (ToDo) task;
                    writer.write("t " + currentTask.getName());
                } else if (task instanceof Deadline) {
                    Deadline currentTask = (Deadline) task;
                    writer.write("d " + currentTask.getName() + " " + currentTask.getDeadline());
                } else if (task instanceof Event) {
                    Event currentTask = (Event) task;
                    writer.write("e " + currentTask.getName() + " " + currentTask.getTime());
                } else {
                    // do nothing
                }

                if (task.isDone()) {
                    writer.write(" done");
                } else {
                    writer.write(" incomplete");
                }
                writer.write("\n");
            } // end for loop
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns ArrayList of Tasks from savedata text file
    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(" ");
                Task newTask;
                if (line[0].equals("t")) {
                    // case ToDo
                    newTask = new ToDo(line[1]);
                } else if (line[0].equals("d")) {
                    // case Deadline
                    newTask = new Deadline(line[1], line[2]);
                } else {
                    // case event
                    newTask = new Event(line[1], line[2]);
                }

                if (line[line.length - 1].equals("done")) {
                    newTask.completeTask();
                }

                list.add(newTask);
            } // end while loop

        } catch (FileNotFoundException e) {
            // returns empty ArrayList if savedata text file not found
            return new ArrayList<Task>();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }

}