import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private final File file;
    
    public Storage(String filePath, String directory) throws IOException {
        File dir = new File(directory);
        file = new File(filePath);
        
        // make the directory if doesn't exist
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        
        // create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }
    }
    
    public List<String> loadData() throws FileNotFoundException {
        List<String> lst = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            lst.add(line);
        }
        return lst;
    }
    
    public void addLine(String line) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(line + "\n");
        fw.close();
    }
    
    public void deleteLine(int lineNum) throws IOException {
        Path path = Path.of(file.getPath());
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        fileContent.remove(lineNum);
        Files.write(path, fileContent, StandardCharsets.UTF_8);
    }
    
    public void modifyLine(int lineNum) throws IOException {
        Path path = Path.of(file.getPath());
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        String line = fileContent.get(lineNum);
        String updatedLine = line.replaceFirst("0", "1");
        fileContent.set(lineNum, updatedLine);
    }
}
