import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {

    public static List<String> ReadFromFile(String path, String fileName) {

        List<String> data = new ArrayList<>();

        File file = new File(path + "/" + fileName);
        try{
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    data.add(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void save(String path, String fileName, List<String> data) {

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdir();
        }

        try {
            FileWriter writer = new FileWriter(path + "/" + fileName);

            for(int i = 0; i < data.size(); i++) {
                writer.write(data.get(i) + System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
