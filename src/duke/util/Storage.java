package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> readFile() {

        File file = new File(filePath);
        List<Task> output = new ArrayList<Task>();

        try {

            if (!file.exists()) {
                return output;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null){

                String[] lineArr = line.split("\\s(\\|)\\s");

                switch (lineArr[0]) {
                    case "T":
                        output.add(new ToDo(lineArr[2]));
                        break;
                    case "D":
                        output.add(new Deadline(lineArr[2], lineArr[3], lineArr[4]));
                        break;
                    case "E":
                        output.add(new Event(lineArr[2], lineArr[3], lineArr[4]));
                        break;
                }

                if (lineArr[1].equals("1")) {
                    output.get(output.size() - 1).completeTask();
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error occurred while reading data");
        }

        return output;
    }

    public void saveFile(List<Task> tasks) {

        File file = new File(filePath);

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            int completed;

            for (Task task : tasks) {
                completed = task.isCompleted() ? 1 : 0;

                if (task instanceof ToDo) {
                    bw.write(String.format("T | %d | %s\n", completed, task.getMsg()));
                }
                else if (task instanceof Deadline) {
                    String time = (((Deadline) task).getTime() != null) ?
                            ((Deadline) task).getTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "NA";
                    bw.write(String.format("D | %d | %s | %s | %s\n", completed, task.getMsg(),
                            ((Deadline) task).getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")), time));
                }
                else if (task instanceof Event) {
                    String time = (((Event) task).getTime() != null) ?
                            ((Event) task).getTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "NA";
                    bw.write(String.format("E | %d | %s | %s | %s\n", completed, task.getMsg(),
                            ((Event) task).getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")), time));
                }
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Error occurred while saving data");
        }
    }
}
