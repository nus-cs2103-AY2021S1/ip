import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    public static File getFile() throws FileNotFoundException, IOException{
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "src", "main", "java", "Data");
        boolean directoryExists = Files.exists(path);

        //checks for the directory
        if (directoryExists) {
            File file1 = new File(home + "/ip/src/main/java/Data/Duke.txt");
            File file2 = new File(home + "/ip/src/main/java/Data/Duke2.txt");

            //Checks which file is to be read
            if (file1.exists()) {
                return file1;
            } else if (file2.exists()) {
                return file2;
            } else {
                //if no file create a new empty file
                Path newFile = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke.txt"));
                return new File(String.valueOf(newFile));
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public static void save(List<Task> list) throws IOException, FileNotFoundException{
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "ip", "src", "main", "java", "Data");
        boolean directoryExists = Files.exists(path);

        //Checks if directory is unchanged
        if (directoryExists) {
            File file1 = new File(home + "/ip/src/main/java/Data/Duke.txt");
                if (file1.exists()) {
                    //creates a new empty file
                    Path newFile = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke2.txt"));
                    FileWriter fw = new FileWriter(home + "/ip/src/main/java/Data/Duke2.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    //deletes the old file
                    Files.delete(Paths.get(home + "/ip/src/main/java/Data/Duke.txt"));

                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        bw.write((i + 1) + "." + task.toString());
                        //Writes task on a new line
                        bw.newLine();
                    }

                    bw.close();
                } else {
                    //creates a new empty file
                    Path newFile = Files.createFile(Path.of(home + "/ip/src/main/java/Data/Duke.txt"));
                    FileWriter fw = new FileWriter(home + "/ip/src/main/java/Data/Duke.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    //deletes the old file
                    Files.delete(Paths.get(home + "/ip/src/main/java/Data/Duke2.txt"));

                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        bw.write((i + 1) + "." + task.toString());
                        //Writes the task on a new line
                        bw.newLine();
                    }

                    bw.close();
                }
        } else {
           throw new FileNotFoundException();
        }
    }
}
