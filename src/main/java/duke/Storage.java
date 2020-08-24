package duke;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

public class Storage {

    String filepath;
    Parser parser;

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
