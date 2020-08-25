import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void readFile() {
        //UPDATE PATH OF TESTSTORAGE
        Storage storage = new Storage("C:/Users/Chunz/Desktop/ip/src/test/java/TestStorage.txt");
        List<String> output = storage.readFile();
        List<String> expectedOutput = readFileFromText("C:/Users/Chunz/Desktop/ip/src/test/java/TestStorage.txt");
        assertEquals(expectedOutput,output);
    }

    List<String> readFileFromText(String filePath) {
        File file = new File(filePath);
        List<String> items = new ArrayList<>();
        if (!file.exists()) {
            return items; //empty list
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String task = s.nextLine();
                items.add(task);
            }
            return items;
        } catch (FileNotFoundException e) {
            return items; //empty list
        }
    }
}
