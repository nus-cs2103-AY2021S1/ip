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

    public List<Task> getFileContents() throws IOException {
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        List<Task> lst = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            String type = Character.toString(line.charAt(1));
            boolean checked = (Character.toString(line.charAt(4))).equals("✓") ? true : false;
            String desc = Task.getDesc(line);
            switch (type) {
                case "T":
                    Task todo = new Todo(desc);
                    todo.setStatus(checked);

                    lst.add(todo);
                    break;
                case "E":
                    String eventDesc = Event.parseFileDesc(desc);
                    String eventAt = Event.parseFileAt(desc);
                    Task event = new Event(eventDesc, eventAt);
                    event.setStatus(checked);

                    lst.add(event);
                    break;
                case "D":
                    String deadlineDesc = Deadline.parseFileDesc(desc);
                    String deadlineBy = Deadline.parseFileBy(desc);
                    Task deadline = new Deadline(deadlineDesc, deadlineBy);
                    deadline.setStatus(checked);

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

    public Task getTask(int i) throws Exception {
        List<Task> filecontents = getFileContents();
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        return filecontents.get(i-1);
    }

    public int getSize() throws Exception {
        List<Task> filecontents = getFileContents();
        return filecontents.size();
    }

    public void addTask(String description) throws Exception {
        appendToFile(String.format("%s\n", description));
    }

    public void completeTask(int i) throws Exception {
        List<Task> filecontents = getFileContents();
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        filecontents.get(i-1).setStatus(true);
        rewriteFileContents(filecontents);
    }

    public void deleteTask(int i) throws Exception {
        List<Task> filecontents = getFileContents();
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        filecontents.remove(i-1);
        rewriteFileContents(filecontents);
    }
}