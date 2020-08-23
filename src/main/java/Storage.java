import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> taskList;

    Storage() {
        this.taskList = new ArrayList<>();
    }

    public static int getPosition(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == target) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Task> readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
            char isDone = line.charAt(4);
            switch (taskType) {
                case 'T':
                    taskList.add(new Todo(line.substring(7)));
                    break;
                case 'E':
                    int posE = getPosition(line, '/');
                    taskList.add(new Event(line.substring(7, posE), line.substring(posE + 4)));
                case 'D':
                    int posD = getPosition(line, '/');
                    taskList.add(new Deadline(line.substring(7, posD), line.substring(posD + 4)));
                default:
                    System.out.println("Can't read line");
            }
        }
        return this.taskList;
    }

    public void writeToFile(String filePath, ArrayList<Task> ls) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        // convert ArrayList contents to string
        for (Task t : ls) {
            textToAdd = textToAdd + t.toString() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
