import java.lang.Throwable;

public class UnknownCommandException extends Throwable {

    protected String command;

    public UnknownCommandException(String s) {
        this.command = s;
    }

}
