import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/** Deals with loading tasks from the file and saving tasks in the file
 * */
public class Storage {
    private String filePath;
    //Default Constructor
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Checks if there is data to draw from. **/
    /** Retrieved from https://nus-cs2103-ay2021s1.github.io/website/book/cppToJava/misc/fileAccess/ */
    public ArrayList<Task> loadFileContents() throws FileNotFoundException {
        // Initialize list to be returned
        ArrayList<Task> list = new ArrayList<>(100);

        File data = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(data); // create a Scanner using the File as the source
        while (s.hasNext()) {
            // Read the data line
            String dataLine = s.nextLine();
            // Split data line into components
            String[] taskDetails = dataLine.split(" - ", 0);
            // Depending on first letter, determine what task it is
            if (taskDetails[0].equals("T")) {
                Task t = new Todo(taskDetails[2]);
                if (taskDetails[1].equals("1")) {
                    t.markedDone(true);
                }
                list.add(t);
            } else if (taskDetails[0].equals("D")) {
                try {
                    // Try to add Deadline based off file
                    Task t = new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]));
                    if (taskDetails[1].equals("1")) {
                        t.markedDone(true);
                    }
                    list.add(t);
                } catch(Exception e) {
                    //If can't, throw Duke's exception
                    // Check if string can be recognized as a valid LocalDate
                    // If can't, print out error message
                    System.out.println("Er, I found an error in the storage data.");
                    System.out.println("This line will be excluded from my task list:\n"
                            + dataLine);
                }
            } else if (taskDetails[0].equals("E")) {
                Task t = new Event(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("1")) {
                    t.markedDone(true);
                }
                list.add(t);
            } else {
                // Basic Error, if invalid task, print error message
                System.out.print("Sorry I could not comprehend this: ");
                System.out.println(dataLine);
            }
            // End of Loop
        }
        // return the final task list
        return list;
    }

    /** Saves Duke's current data **/
    public void saveToFile(ArrayList<Task> list) throws DukeException {
        try {
            // Create file if there is none
            createFile();

            // Empties file if there is one
            clearTheFile();

        } catch (IOException e) {
            throw new DukeException("☹️Sorry, something went wrong and I couldn't save my data... ");
        }
        for (int i = 0; i < list.size(); i++) {
            // Try to write into save file
            try {
                writeToFile(list.get(i).toSaveData());
            } catch (IOException e) {
                System.out.print("Something happened and this failed to be saved: ");
                System.out.println(list.get(i));
            }
        }
    }

    /** Writes Data to File **/
    /** Retrieved from https://nus-cs2103-ay2021s1.github.io/website/book/cppToJava/misc/fileAccess/ */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAdd);
        fw.close();
    }

    /** Clear the save file of content. */
    /** Retrieved from
     * https://stackoverflow.com/questions/29878237
     *      /java-how-to-clear-a-text-file-without-deleting-it/42282671*/
    private void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter(filePath, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

    /** Creation of data to draw from. **/
    private void createFile() {
        // Make directory if it doesn't already exists
        File dir = new File("data");
        dir.mkdir();

        // create a File for the given file path
        new File(filePath);
    }
}
