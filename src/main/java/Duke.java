import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        //  Load the data from the hard disk when Duke starts up.
        File taskFile = new File("data/duke.txt");
        if (!taskFile.exists()) {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileToListReader fileReader = new FileToListReader(taskFile);
            list = fileReader.getList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Message start = new Message("start", list, taskFile);
        try {
            start.reply();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (sc.hasNext()) {
            Message msg = new Message(sc.nextLine(), list, taskFile);
            try {
                msg.reply();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            if (msg.getCmd() == Command.BYE) {
                break;
            }
        }
    }
}
