package Duke.Helpers;
import Duke.Errors.DukeException;
import Duke.Errors.FIleEmptyException;
import Duke.Errors.FileAbsentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }
    public List<String> load() throws DukeException {
        File f = new File(this.filePath);
        try {
            List<String> xs = new ArrayList<>();
            Scanner sc = new Scanner(f);
            if(sc.hasNext()) {
                do{
                    xs.add(sc.nextLine());
                    } while (sc.hasNextLine());
                }
            if(xs.size() == 0){
                throw new FIleEmptyException();
            }
            FileWriter fw = new FileWriter(this.filePath);
            fw.write("");
            fw.close();
            return xs;
        } catch (IOException error){
            throw new FileAbsentException(this.filePath);
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
