import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SavedTaskList {
    protected static void editSavedTaskList(String pathName, String newTaskList) throws IOException {
        FileWriter fw = new FileWriter(pathName);
        fw.write(newTaskList);
        fw.close();
    }
    
    protected static void readSavedTaskList(File savedTaskList) throws FileNotFoundException {
        try {
            savedTaskList.createNewFile();
            Scanner sc = new Scanner(savedTaskList);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
