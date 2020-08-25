package duke;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

/**
 * This class provides an interface for keeping and retrieving the tasks on hard disk memory
 */
public class Storage {

    String filepath;
    Parser parser;

    /**
     * Constructor for instantiating a Storage
     *
     * @param filepath Specifies which file path should this Storage be working on
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.parser = new Parser();
    }


    private void createNewTextFileCalledTask() {
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method provides the save feature for the tasks
     *
     * @param lstOfTask The list provided by Duke after execution
     */
    public void saveTaskContents(List<Task> lstOfTask) {
        try {
            FileWriter fw = new FileWriter("Tasks.txt");

            createNewTextFileCalledTask();


            for (Task t : lstOfTask) {
                String taskString = t.toString();
                fw.write(taskString + "\n");
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method provides the load feature for task to be loaded onto a list.
     *
     * @param lstOfTask The list that data will be populated on.
     * @throws IOException The exception arises when there are issues reading from a file
     */
    public void populateToLstOfTask(List<Task> lstOfTask) throws IOException {

        File file = new File("Tasks.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = br.readLine();

        while (st != null) {
            Task t = parser.parseTxtToTask(st);
            lstOfTask.add(t);
            st = br.readLine();
        }

    }

}
