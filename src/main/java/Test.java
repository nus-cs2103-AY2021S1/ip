import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            PrintWriter pw = new PrintWriter(fw);


            pw.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
