import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.Scanner;

import java.lang.StringBuilder;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> read() throws FileNotFoundException, CorruptedFileException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        ArrayList<String> lineList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lineList.add(scanner.nextLine());
        }
        try {
            return lineConverter(lineList);
        } catch (CorruptedFileException e) {
            throw new CorruptedFileException("Sorry, the input file is corrupted.");
        }
    }

    public void write(String filePath, ArrayList<Task> textToAdd) throws IOException {
        File f = new File(filePath);
        File dataDir = new File(f.toPath().getParent().toString());
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskConverter(textToAdd));
        fileWriter.close();
    }

    private ArrayList<Task> lineConverter(ArrayList<String> lineList) throws CorruptedFileException {
        ArrayList<Task> taskList = new ArrayList<>();
        for (String s : lineList)  {
            String[] temp = s.split(" \\| ");
            if (!temp[1].equals("1") && !temp[1].equals("0")) {
                throw new CorruptedFileException("Sorry, the input file is corrupted.");
            }
            if (temp[0].equals("T")) {
                taskList.add(new Todo(temp[2], func.apply(temp[1])));
            } else if (temp[0].equals("D")) {
                taskList.add(new Deadline(temp[2], func.apply(temp[1]), temp[3]));
            } else if (temp[0].equals("E")) {
                taskList.add(new Event(temp[2], func.apply(temp[1]), temp[3]));
            } else {
                throw new CorruptedFileException("Sorry, the input file is corrupted.");
            }
        }
        return taskList;
    }

    private String taskConverter(ArrayList<Task> taskList) {
        StringBuilder str = new StringBuilder();
        for (Task t : taskList) {
            str.append(t.toSave()).append("\n");
        }
        return str.toString();
    }

    private Function<String, Boolean> func = x -> x.equals("1") ? true : false;
}
