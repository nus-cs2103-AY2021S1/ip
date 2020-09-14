import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static File file;
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

    /**
     * read the content of the file in bill.txt
     * @param file The file to be read.
     */
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


    /**
     * Stores the updated Lists that contain the tasks to bill.txt.
     * @param listOfContent TaskList that manages tasks.
     */
    public static void write(TaskList listOfContent) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            for (Task task : listOfContent.getTheList()) {
                fileWriter.write(task.timeConverted() + "\n");
            }
            fileWriter.close();
            read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
