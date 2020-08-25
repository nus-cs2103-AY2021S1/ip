import java.io.File;
import java.io.IOException;

/**
 * Encapsulates the handling of Duke's save file.
 */
public class DukeSaveFile {

    private static String DIRECTORY_PATH = "../data";
    private static String FILE_PATH = "../data/duke.txt";

    /**
     * Checks if duke.txt file exists in [project_root]/data.
     * If it does not exist, create one.
     */
    public static void checkSaveFile() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);
        if (directory.exists()) {

        } else {
            // Create data directory
            boolean hasCreatedDir = directory.mkdir();
            if (hasCreatedDir) {
                System.out.println("A data directory has been successfully created at the root folder.");
            } else {
                System.out.println("A data directory cannot be created. Please try again.");
            }

            // Create duke.txt
            String failMessage = "A text file cannot be created. Please try again.";
            try {
                boolean hasCreatedFile = file.createNewFile();
                if (hasCreatedFile) {
                    System.out.println("A text file has been successfully created at [root_folder]/data.");
                } else {
                    System.out.println(failMessage);
                }
            } catch (IOException e) {
                System.out.println(failMessage);
            }
        }
    }
}
