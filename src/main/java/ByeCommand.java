import java.util.concurrent.TimeUnit;

/**
 * Represents a ByeCommand command which is a subclass of Command.
 */
public class ByeCommand extends Command {
    private final static int TIMEOUT = 2;

    /**
     * Creates a ByeCommand object.
     * It generates a goodbye message and then closes the program.
     */
    public ByeCommand() {
        super("See you again!");
    }

    /**
     * Closes the program by concurrently after delaying for 2 seconds.
     */
    public void closeProgram() {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(TIMEOUT);
                MainWindow.closeWindow();

            } catch (InterruptedException ie) {
                System.out.println("Unable to sleep");
            }
        });

        t1.start();
    }

    /**
     * Returns the goodbye message.
     *
     * @return a String of goodbye message as Duke response.
     */
    public String getOutput() {
        closeProgram();
        return this.toString();
    }
}


