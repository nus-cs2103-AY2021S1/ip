import java.util.HashMap;

public abstract class ComplexCommand extends Command{

    public String params;
    public ComplexCommand(String params) {
        this.params = params;
    }

}
