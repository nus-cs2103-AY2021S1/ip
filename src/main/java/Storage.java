import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage { //deals with loading task and saving task in file

    private String filePath;
    private File file;

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

    public ArrayList<Listing> load() {
        ArrayList<Listing> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String[] details = sc.nextLine().split(" \\| ");
                switch (details[0]) {
                    case "T":
                        list.add(new ToDo(details[1], details[2]));
                        break;
                    case "D":
                        list.add(new Deadline(details[1], details[2], details[3]));
                        break;
                    case "E":
                        list.add(new Event(details[1], details[2], details[3]));
                        break;
                }
                //add to list
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

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


        //parse list
        //write to duke.txt
        return;
    }


}
