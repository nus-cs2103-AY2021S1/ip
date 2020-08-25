import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage { //deals with loading task and saving task in file

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File myFile = new File(filePath);
            if (!myFile.exists()) { //if no file
                myFile.createNewFile();
                System.out.println("new duke.txt created");
            } else if (myFile.exists()) { // if file exist
                System.out.println("file exists");
            } else { //error
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public ArrayList<String> load() {
        return new ArrayList<String>();
    }

    public void save() {
        return;
    }

}
