import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {
    private String path;
    
    public ReadFile(String filePath) {
        path = filePath;
    }
    
    private int readLines() throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        
        String nextLine;
        int numLines = 0;
        
        while ((nextLine = bufferedReader.readLine()) != null) {
            ++numLines;
        }
        
        bufferedReader.close();
        return numLines;
    }
    
    public String[] openFile() throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
   
        int numLines = readLines();
        String[] data = new String[numLines];
   
        for (int i = 0; i < numLines; ++i) {
            data[i] = bufferedReader.readLine();
        }
        
        bufferedReader.close();
        return data;
    }
}
