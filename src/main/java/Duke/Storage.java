package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Storage {
    /** The task list */
    private ArrayList<String> lines;
    /** The directoryPath */
    private String directoryPath;
    /** The filePath */
    private String filePath;
    /** The File object with filePath as its param */
    private File data;

    /**
     * The constructor for the Storage class.
     *
     * @param directoryPath the path to the directory containing the data.txt file.
     * @param filePath the path to the data.txt file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
        this.lines = new ArrayList<String>();
    }

    /**
     * Obtains the file required to begin Duke. If the files/folders are missing, they would be created in the
     * specified directories. It then converts the txt file into an ArrayList{@link ArrayList} containing strings that
     * represent the tasks specified in the txt file.
     * <p>
     * It is important to know that the arrayList is not updated throughout the use of Duke. The goal of this class is
     * to only process the initial data.txt file upon loading and saving a final updated copy of the txt file which has
     * to be provided. Updates occur in the Parser{@link Parser} class.
     *
     * @throws IOException An exception is thrown as the file and directory paths specified might be invalid or cannot
     * be found. In this case, the exception can be ignored as the method creates the directories as needed.
     */
    public void processData() throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        data = new File(filePath);
        if (!data.exists()) {
            data.createNewFile();
        }
        Scanner fileReader = new Scanner(data);
        StringBuffer buffer = new StringBuffer();
        while (fileReader.hasNextLine()) {
            buffer.append(fileReader.nextLine()).append("\n");
        }
        fileReader.close();
        String fileContents = buffer.toString();
        if (fileContents.length() != 0) {
            String[] lineArray = fileContents.split("\n");
            Collections.addAll(lines, lineArray);
        }
    }

    /**
     * Simply returns the ArrayList. Call this method only after calling processData or else you will only get an empty
     * ArrayList.
     *
     * @return the ArrayList stored in the class.
     */
    public ArrayList<String> getData() {
        return lines;
    }

    /**
     * Saves into the txt file, located in the path specified when creating the storage object, a list of tasks which
     * is obtained from the supplied ArrayList<String> parameter. The old txt file is overwritten as a result. Call this
     * method only after calling processData.
     *
     * @param finalLines the list of tasks to be saved
     * @throws FileNotFoundException Thrown by the PrintWriter class as there is the possibility that the file path is
     * invalid. Fortunately, by calling processData first, this exception can be ignored as it is addressed in that
     * method.
     */
    public void saveData(ArrayList<String> finalLines) throws FileNotFoundException {
        StringBuffer finalLineList = new StringBuffer();
        for (int i = 0; i < finalLines.size(); i++) {
            String currentLine = finalLines.get(i);
            finalLineList.append(currentLine).append("\n");
        }
        PrintWriter prw = new PrintWriter(data);
        prw.println(finalLineList.toString());
        prw.close();
    }
}
