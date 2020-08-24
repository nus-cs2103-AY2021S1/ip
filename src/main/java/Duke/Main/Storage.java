package Duke.Main;

import Duke.Tasks.*;

import java.io.File;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        File file = new File(filepath);

        //Create the file if it does not exist
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Error occurred while creating file in Storage");
        }

        this.filepath = filepath;
    }

    /**
     * Resets the entire txt file
     * @throws IOException
     */
    private void resetFile() throws IOException {
        FileWriter fw = new FileWriter(filepath, false);
        fw.close();
    }

    /**
     * Appends string to txt file
     * @param textToAppend
     * @throws IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filepath, true); // create a FileWriter in append mode
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToAppend);
        bw.close();
    }

    /**
     * Returns the entire txt file
     * @return List of Tasks from file
     * @throws IOException
     */
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
                    Task event = new Event(desc, checked, Parser.strToDate(arr[3]));
                    lst.add(event);
                    break;
                case "D":
                    Task deadline = new Deadline(desc, checked, Parser.strToDate(arr[3]));
                    lst.add(deadline);
                    break;
                default:
                    break;
            }
            line = br.readLine();
        }
        br.close();
        return lst;
    }

    /**
     * Rewrites the file contents, each line corresponding to each element of list
     * @param lst
     */
    public void rewriteFileContents(List<Task> lst) {
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
}