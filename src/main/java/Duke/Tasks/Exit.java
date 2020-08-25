package Duke.Tasks;

/**
 * The Exit command when user want to quit the Duke.
 */
public class Exit extends Task{

    public Exit() {
        super("exit", true);
        this.isExit = true;
    }

}
