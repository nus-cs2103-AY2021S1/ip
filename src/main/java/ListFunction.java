import java.util.List;

public class ListFunction implements Function {
    @Override
    public void execute(String args, Bot bot, List<String> list) {
        if (list.isEmpty()) {
            bot.sayLine("There are no items in your list.");
        }
        for (int i = 0; i < list.size(); i++) {
            bot.sayLine((i + 1) + ". " + list.get(i));
        }
    }
}
