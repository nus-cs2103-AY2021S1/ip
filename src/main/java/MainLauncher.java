import duke.Duke;
import duke.gui.Launcher;

public class MainLauncher {
    public static void main(String[] args) {
        boolean runningTerminal = false;
        if (runningTerminal) {
            Duke.main(args);
        } else {
            Launcher.main(args);
        }
    }
}
