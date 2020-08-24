import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static TaskList list;

    private static void makeDataFile() throws IOException {
        File file = Paths.get(".", "data", "duke.data").toFile();
        list = new TaskList();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    public static void main(String[] args) {
        Ui.printGreeting();

        try {
            File file = Paths.get(".", "data", "duke.data").toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (TaskList) ois.readObject();
            ois.close();
            //@@author
        } catch (IOException | ClassNotFoundException e1) {
            try {
                makeDataFile();
            } catch (FileNotFoundException e2) {
                try {
                    Path dataFolder = Paths.get(".", "data");
                    Files.createDirectories(dataFolder);
                    makeDataFile();
                } catch (IOException e) {
                    Ui.print(e.getMessage());
                }
            } catch (IOException e) {
                Ui.print("There was an error initialising your save file.\n" + e);
            }
        }

        Ui ui = new Ui();

        String input = ui.getInput();
        Command command = Parser.parse(input);
        while (command.getTaskType() != TaskType.BYE) {
            command.execute(list);

            input = ui.getInput();
            command = Parser.parse(input);
        }

        try {
            File file = Paths.get(".", "data", "duke.data").toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            //@@author
        } catch (IOException e) {
            Ui.print("There was an error saving your data.\n" + e.getMessage());
        }

        Ui.printGoodbye();
    }
}
