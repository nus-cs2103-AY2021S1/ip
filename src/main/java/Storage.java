import Exceptions.DukeException;
import Exceptions.FIleEmptyException;
import Exceptions.FileAbsentException;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    Storage(String filePath){
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
            return xs;
        }catch (FileNotFoundException error){
            throw new FileAbsentException();
        }
    }
}
