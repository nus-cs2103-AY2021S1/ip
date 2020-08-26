package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String dir_path = "data";
    private final String txt_path = "data/duke.txt";
    private String path;

    public Storage(String path) {
        this.path = path;
        try {
            createFolder();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void createFolder() throws IOException {
        File dir_folder = new File(dir_path);
        File txt = new File(txt_path);
        if (!dir_folder.exists()) {
            dir_folder.mkdir();
        }
        if (!txt.exists()) {
            txt.createNewFile();
        }
    }

    public List<Task> loadSavedData() {
        List<Task> list = new ArrayList<>();

        try {
            File file = new File(txt_path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] splited = line.split(">");
                Task currTask = null;
                if (splited[0].equals("T")) {
                    currTask = new Todo(splited[2]);
                } else if (splited[0].equals("E")) {
                    currTask = new Event(splited[2], splited[3]);
                } else if (splited[0].equals("D")) {
                    currTask = new Deadline(splited[2], splited[3]);
                }
                if (splited[1].equals("1")) {
                    currTask.markAsDone();
                }
                list.add(currTask);
            }
        } catch(IOException e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public void addTask(String task) throws IOException {
        File file = new File(txt_path);
        FileWriter writer = new FileWriter(file, true);
        writer.write(task);
        writer.write('\n');
        writer.flush();
        writer.close();
    }

    public void completeTask(int number) throws IOException {
        File file = new File(txt_path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int line_no = 0;
        String final_line = "";
        while((line = br.readLine()) != null) {
            line_no += 1;
            if (line_no == number) {
                StringBuilder updated = new StringBuilder(line);
                updated.setCharAt(2, '1');
                final_line += updated + "\n";
            } else {
                final_line += line + "\n";
            }
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(final_line);
        writer.flush();
        writer.close();
    }

    public void deleteTask(int number) throws IOException {
        File file = new File(txt_path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int line_no = 0;
        String final_line = "";
        while((line = br.readLine()) != null) {
            line_no += 1;
            if (line_no == number) {
                continue;
            } else {
                final_line += line + "\n";
            }
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(final_line);
        writer.flush();
        writer.close();
    }
}
