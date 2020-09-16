import java.util.concurrent.TimeUnit;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("See you again!");
    }

    public void closeProgram() {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
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


