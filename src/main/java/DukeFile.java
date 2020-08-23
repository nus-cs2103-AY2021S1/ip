import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DukeFile {

    public File retrieveFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public void saveToFile(String filepath, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(text);
        System.out.println("_________________________________________\n" + "Text: " + text + " saved into " + filepath + "\n_________________________________________");
        fileWriter.close();
    }



}

