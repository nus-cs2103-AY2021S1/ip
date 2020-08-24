import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DukeFile {
    private String fileName;
    private ArrayList<String> recordArrayLst;

    private DukeFile(String fileName) {
        this.fileName = fileName;
        this.recordArrayLst = new ArrayList<>();
    }

    public File retrieveFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public void saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (String s : recordArrayLst) {
            fileWriter.write(s + "\n");
            System.out.println("*****************************************\n" + "Text: " + s + " saved into " + fileName + "\n*****************************************");
        }

        fileWriter.close();
    }

    public void saveRecord(String record) {
        recordArrayLst.add(record);
    }

    public void updateRecord(String record, int index) {
        int i = index -1;
        recordArrayLst.set(i, record);
    }

    public void deleteRecord(int index) {
        int i = index - 1;
        recordArrayLst.remove(i);
    }

    public static DukeFile createDukeFile(String fileName) {
        return new DukeFile(fileName);
    }

    public ArrayList<String> getRecords() {
        return this.recordArrayLst;
    }
}

