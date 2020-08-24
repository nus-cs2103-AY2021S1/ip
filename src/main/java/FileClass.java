import java.io.File;

public class FileClass {
    public static void main(String[] args) {
        File f = new File("data/duke.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }
}
