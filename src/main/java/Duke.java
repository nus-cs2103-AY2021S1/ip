import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Duke.*;

public class Duke {

    /**
     * The main method, when run, runs the Duke Program.
     *
     * @param args NA
     */
    public static void main(String[] args) {
        Storage storage = new Storage("src/main/data/", "src/main/data/data.txt");
        try {
            storage.processData();
        } catch (java.io.IOException ignored) {

        }
        ArrayList<String> lines = storage.getData();
        Parser parser = new Parser(lines);
        Scanner input = new Scanner(System.in);
        Ui.introduction();
        while(parser.continueDuke()) {
            String inputString = input.nextLine();
            try {
                parser.parse(inputString);
                if (!parser.continueDuke()) {
                    ArrayList<String> finalLines = parser.finalizedLines();
                    storage.saveData(finalLines);
                }
            } catch (DukeException e) {
                Ui.handleDukeException(e);
            } catch (IOException ignored) {

            }
        }
    }
}
