import java.util.Arrays;
import java.util.List;

public class ErrorFunction implements Function {
    private List<String> message;

    public ErrorFunction(List<String> message) {
        this.message = message;
    }

    public ErrorFunction(String message) {
        this(Arrays.asList(message));
    }

    @Override
    public void execute(Bot bot, List<Task> list) {
        bot.sayLines(message);
    }
}
