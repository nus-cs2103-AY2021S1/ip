import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        File output = new File("data");
        System.out.println(output.mkdir());

        FileWriter fileWriter = new FileWriter( "./data/duke.txt");
        try{
            fileWriter.write("asdf\n");
            fileWriter.write("ert");
            fileWriter.close();
            System.out.println("write");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
