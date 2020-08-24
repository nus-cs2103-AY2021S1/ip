import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Storage {

    protected String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    //Remove done string and add in delimeter
    public String editFileInput(String[] input) {
        ArrayList<String> result = new ArrayList<>();
        String taskType = input[0];
        for (int i = 0; i < input.length; i++) {
            if (taskType.equals("deadline") && i == 3) {
                result.add("/by");
            } else if (taskType.equals("event") && i == 3) {
                result.add("/at");
            }
            if (i != 1) {
                result.add(input[i]);
            }
        }
        String returnArray[] = new String[result.size()];
        return Arrays.stream(result.toArray(returnArray)).collect(Collectors.joining(" "));
    }

    public TaskList load() throws DukeException {
        try {
            File file = new File(this.filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            TaskList newTaskList = new TaskList();
            while (line != null) {
                String[] splitLine = line.split(" \\| ");
                boolean isDone = splitLine[1].equals("1") ? true : false;
                AddCommand c = new Parser(editFileInput(splitLine)).parseFromFile(isDone);
                c.executeFromFile(newTaskList);
                line = br.readLine();
            }
            br.close();
            fr.close();
            return newTaskList;
        } catch (IOException e) {
            throw new DukeException("Sorry, there were some issues reading the file located at: " + this.filePath
                    + ": " + e.getMessage());
        }
    }

    public void write(TaskList taskList) throws DukeException {
        try {
            File file = new File(filePath);
            // add directory if it does not exist
            file.getParentFile().mkdirs();
            FileWriter fw;

            if (file.exists()) {
                fw = new FileWriter(file, false);
            } else {
                fw = new FileWriter(file, true);
            }

            for (Task i : taskList.getTaskList()) {
                fw.write(i.appendFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, there were some issues writing to the file located at: " + this.filePath
                    + ": " + e.getMessage());
        }
    }
}
