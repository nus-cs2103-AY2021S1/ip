import duke.Ui;
import duke.Duke;

public class Main {

    public static void main(String[] args) {
        Duke duke = Duke.createDuke("data/duke.txt");
        Ui ui = new Ui();

        try {
            if (duke == null) {
                throw new NullPointerException();
            } else {
                duke.run();
            }
        } catch (NullPointerException e) {
            System.err.println(ui.printFormat("Unable to create bot!\n"));
        }

    }

}