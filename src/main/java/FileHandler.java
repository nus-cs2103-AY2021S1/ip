import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class FileHandler {
    private String filepath;

    public FileHandler(String filepath) {
        this.filepath = filepath;
//        addTask("random stuff");
        try {
//            resetFile(filepath);
//            System.out.println(getFileContents(filepath));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void resetFile() throws IOException {
        FileWriter fw = new FileWriter(filepath, false);
        fw.close();
    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filepath, true); // create a FileWriter in append mode
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToAppend);
        bw.close();
    }

    private List<Task> getFileContents() throws IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        List<Task> lst = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            String type = Character.toString(line.charAt(1));
            String desc = Task.getDesc(line);
            switch (type) {
                case "T":
                    Task todo = new Todo(desc);
                    lst.add(todo);
                    break;
                case "E":
                    String eventDesc = Event.parseFileDesc(desc);
                    String eventAt = Event.parseFileAt(desc);
                    Task event = new Event(eventDesc, eventAt);
                    lst.add(event);
                    break;
                case "D":
                    String deadlineDesc = Deadline.parseFileDesc(desc);
                    String deadlineBy = Deadline.parseFileBy(desc);
                    Task deadline = new Deadline(deadlineDesc, deadlineBy);
                    lst.add(deadline);
                    break;
                default:
                    break;
            }
            line = br.readLine();
        }
        return lst;
    }

    private void rewriteFileContents(List<Task> lst) {
        try {
            resetFile();
            for (int i = 0; i < lst.size(); i++) {
                appendToFile(String.format("%s\n", lst.get(i)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addTask(String description) {
        try {
            appendToFile(String.format("%s\n", description));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void completeTask(int i) {
        try {
//            List<Task> lst = getFileContents();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteTask(int i) {

    }
}