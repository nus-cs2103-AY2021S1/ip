import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        IntroModule.display();

        FileReading fileReader = new FileReading();
        String FILE_PATH = "data/duke.txt";

        try {
            fileReader.printFileContents(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        StringIdentifier strIden = new StringIdentifier(fileReader.getTaskList());
        /*
        System.out.println("    _______________________________________________________________________");
        strIden.displayList();
        System.out.println("    _______________________________________________________________________\n");
         */

        Scanner sc = new Scanner(System.in);
        FileWriting fileWriter = new FileWriting();

        while (strIden.isRunning()) {
            String userInput = sc.nextLine();

            try {
                strIden.checker(userInput);
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }

            try {
                fileWriter.writeToFile(FILE_PATH, strIden.getTasks());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
