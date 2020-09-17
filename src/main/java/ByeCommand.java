import java.util.concurrent.TimeUnit;

public class ByeCommand extends Command {
    private final static int TIMEOUT = 2;

    public ByeCommand() {
        super("See you again!");
    }

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

    public String getOutput() {
        closeProgram();
        return this.toString();
    }
}


