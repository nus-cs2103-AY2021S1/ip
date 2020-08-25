import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;


    Storage (String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("I will open up the file now");
            read(file);
        } else {
            System.out.println("The file is not exist, i will create a file for the Tasks now");
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.close();
                read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        this.filePath = filePath;
    }

    public static void read(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if (!scanner.hasNextLine()) {
            System.out.println("There is no tasks in the list yet, please add in the tasks now");
        } else {
            System.out.println("The Tasks are shown here: ");
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                System.out.println(currentLine);
            }
        }
    }
//    public static void write(List<Task> list_of_Content) {
//        try {
//            FileWriter fileWriter = new FileWriter(filePath, true);
//            for (Task task : list_of_Content) {
//                fileWriter.write(task.timeConverted() + "\n");
//            }
//            fileWriter.close();
//            read(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void write(List<Task> list_of_Content) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            for (Task task : list_of_Content) {
//                fileWriter.write(task.fileFormat() + "\n");
                fileWriter.write(task.timeConverted() + "\n");
            }
            fileWriter.close();
            read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
