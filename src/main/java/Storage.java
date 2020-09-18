import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    protected final File TASKFILE;
    protected String createResult = "";

    private String filePath;
    private String dirPath;

    // Constructor
    public Storage(String filePath, String dirPath) {

        this.filePath = filePath;
        this.dirPath = dirPath;

        this.TASKFILE = new File(filePath);

        try {
            if (TASKFILE.createNewFile()) {
                // if a tasks.txt file does not exist, we create a file so that we can read from it in the future
                this.createResult = "     Duke has noticed that you do not have a text file to store your tasks!\n"
                        + "     As such, Duke has created an empty file, ready to store your tasks!\n"
                        + "     This text file can be found at: " + TASKFILE.getAbsolutePath();
            } else {
                this.createResult = "     Duke has noticed that you have a text file to store your tasks!\n"
                        + "     Duke is currently reading the file from: " + TASKFILE.getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // writes the list of tasks into the file
    public void write(ArrayList<Task> tasks) {

        try {
            // create a FileWriter object used by Duke to write to the taskFile
            FileWriter writeTaskFile = new FileWriter(this.TASKFILE);

            for (Task task : tasks) {
                if (task instanceof Event) {

                    String day = ((Event) task).date.getDayOfMonth() + "/" + ((Event) task).date.getMonthValue() + "/"
                            + ((Event)task).date.getYear();
                    String time = String.format("%02d", ((Event) task).time.getHour())
                            + String.format("%02d", ((Event) task).time.getMinute());
                    writeTaskFile.write("event" + " " + task.description + " " + "/by" + " " + day + " " + time
                            + " " + task.isDone + System.lineSeparator());
                } else if (task instanceof Deadline) {
                    String day = ((Deadline) task).date.getDayOfMonth() + "/" + ((Deadline) task).date.getMonthValue()
                            + "/" + ((Deadline) task).date.getYear();
                    String time = String.format("%02d", ((Deadline) task).time.getHour())
                            + String.format("%02d", ((Deadline) task).time.getMinute());

                    writeTaskFile.write("deadline" + " " + task.description + " " + "/by" + " " + day + " " + time
                            + " " + task.isDone + System.lineSeparator());
                } else if (task instanceof ToDo) {
                    writeTaskFile.write("todo" + " " + task.description + " " + task.isDone
                            + System.lineSeparator());
                }

            }
            writeTaskFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads the file contents into the provided list of tasks
    public void read(ArrayList<Task> tasks) {
        Path filePath = Paths.get(TASKFILE.getAbsolutePath());

        try {
            List<String> taskList = Files.readAllLines(filePath);
            if (taskList.size() != 0) {
                for (String task : taskList) {

                    String[] params = task.split(" ");

                    switch (params[0]) {
                    case ("event"):
                        tasks.add(new Event(params[1], params[3] + " " + params[4],
                                Boolean.parseBoolean(params[5])));
                        break;
                    case ("deadline"):
                        tasks.add(new Deadline(params[1], params[3] + " " + params[4],
                                Boolean.parseBoolean(params[5])));
                        break;
                    case ("todo"):
                        tasks.add(new ToDo(params[1], Boolean.parseBoolean(params[2])));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loads the file contents and returns them in a list format
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> resultList = new ArrayList<>();
        read(resultList);
        return resultList;
    }
}
