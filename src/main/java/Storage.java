import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * The Storage class is tasked with checking that the a duke.txt file is created given the file path
 * initially and creating it if it is not found. It also manages the saving and loading of duke.txt.
 */

public class Storage { //deals with loading task and saving task in file

    private String filePath; //check
    private File file;

    /**
     * Creates a new Storage object. Storage is initialised with filepath passed from Bot.java.
     * Filepath is assigned to this.filePath. Storage checks if there is a file named duke.txt (from
     * the file path) that exists. If it does, the file is assigned to this.file.
     *
     * @param filePath file path of duke.txt (or other file name as determined by creator)
     * @return a Storage object
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            File myFile = new File(filePath);
            boolean exists = myFile.exists();
            if (!exists) {
                myFile.createNewFile();
                this.file = myFile;
                System.out.println("new duke.txt created");
            } else if (exists) {
                this.file = myFile;
                System.out.println("file exists");
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new ArrayList<Listing>. If this.file contains a non-empty file, the lines are parsed
     * and split using a Scanners to return a string array of size 3 containing the code, isDone,
     * details and timedetail if applicable (or left null if none is available). The corresponding
     * Listing is then created and added to the Arraylist which is then returned. If file is empty
     * then an empty ArrayList is returned.
     *
     * @return An ArrayList<Listing> based after parsing duke.txt
     */
    public ArrayList<Listing> load() {
        ArrayList<Listing> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String[] details = sc.nextLine().split(" \\| ");
                switch (details[0]) {
                    case "T":
                        if (details.length == 4) {
                            list.add(new ToDo(details[1], details[2], details[3]));
                        } else {
                            list.add(new ToDo(details[1], details[2]));
                        }
                        break;
                    case "D":
                        if (details.length == 5) {
                            list.add(new Deadline(details[1], details[2], details[3], details[4]));
                        } else {
                            list.add(new Deadline(details[1], details[2], details[3]));
                        }
                        break;
                    case "E":
                        if (details.length == 5) {
                            list.add(new Event(details[1], details[2], details[3], details[4]));
                        } else {
                            list.add(new Event(details[1], details[2], details[3]));
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * Takes in an ArrayList<Listing> and loops through the list, writing the details of each listing
     * separated by a " | " into this.file.
     *
     * @param list the ArrayList<Listing> from TaskList.java
     */
    public void save(ArrayList<Listing> list) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Listing listing : list) {
                String[] details = listing.toArray();
                String s = "";
                for (int i = 0; i < details.length; i++) {
                    s = s + details[i];
                    if (i == details.length - 1) {
                        break;
                    }
                    s = s + " | ";
                }
                fileWriter.write(s + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return;
    }

}
