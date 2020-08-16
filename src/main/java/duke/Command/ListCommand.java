package duke.Command;
import duke.Message;

public class ListCommand extends Command {

    @Override
    public String execute(String string) {

        StringBuilder list = new StringBuilder();

        if (listArray.isEmpty()) {
            return string + " is empty";
        } else {

            for (int i = 1; i <= listArray.size(); i++) {
                if (i == listArray.size()) {
                    list.append(i).append(". ").append(listArray.get(i - 1));
                } else {
                    list.append(i).append(". ").append(listArray.get(i - 1))
                            .append("\n").append(Message.INDENT);
                }
            }

            return list.toString();
        }
    }
}
