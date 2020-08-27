import duke.Duke;
import duke.Ui;

/**
 * The <code>Main</code> class is the application's entry point. It starts the entire application by creating a
 * <code>Duke</code> object and checking for existing tasks.
 */
public class Main {

    /**
     * Creates a <code>Duke</code> object.
     * @param args array for command-line arguments
     */
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
