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
            String[] arr = line.split(",");
            String type = arr[0];
            String checked = arr[1];
            String desc = arr[2];
            switch (type) {
                case "T":
                    Task todo = new Todo(desc, checked);
                    lst.add(todo);
                    break;
                case "E":
                    Task event = new Event(desc, checked, Duke.strToDate(arr[3]));
                    lst.add(event);
                    break;
                case "D":
                    Task deadline = new Deadline(desc, checked, Duke.strToDate(arr[3]));
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
                String[] arr = lst.get(i).getStringArr();
                String s = Task.stringFormat(arr);
                appendToFile(s);
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

    public void addTask(String[] arr) throws Exception {
        appendToFile(Task.stringFormat(arr));
    }

    public Task completeTask(int i) throws Exception {
        List<Task> filecontents = getFileContents();
        Task t = filecontents.get(i-1);
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        filecontents.get(i-1).setStatus("1");
        rewriteFileContents(filecontents);
        return t;
    }

    public Task deleteTask(int i) throws Exception {
        List<Task> filecontents = getFileContents();
        Task t = filecontents.get(i-1);
        if (i <= 0 || i > filecontents.size()) {
            throw new InvalidIndexException();
        }
        filecontents.remove(i-1);
        rewriteFileContents(filecontents);
        return t;
    }
}