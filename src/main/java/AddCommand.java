import java.util.Arrays;

public class AddCommand extends Command {

    private final String desc;

    AddCommand(String description) {
        this.desc = description;
    }

    public static String getStringWithoutKeyword(String[] strArr) {
        return String.join(" ", Arrays.copyOfRange(strArr, 1, strArr.length));
    }

    public static String getDate(String str, String delimiter) {
        return str.split(delimiter)[1];
    }

    public static String getWithoutDelimiter(String str, String delimiter) {
        return str.split(delimiter)[0];
    }

    public String execute() {

        String[] words = this.desc.split("\\s+");
        String keyword = words[0];

        String stringWithoutKeyword;
        String date;
        String stringWithoutDelimiter;

        switch (keyword) {
            case "todo":
                stringWithoutKeyword = getStringWithoutKeyword(words);
                listArray.add(new ToDo(stringWithoutKeyword));
                break;
            case "deadline":
                stringWithoutKeyword = getStringWithoutKeyword(words);
                date = getDate(stringWithoutKeyword, Deadline.delimiterBy);
                stringWithoutDelimiter = getWithoutDelimiter(stringWithoutKeyword, Deadline.delimiterBy);
                listArray.add(new Deadline(stringWithoutDelimiter, date));
                break;
            case "event":
                stringWithoutKeyword = getStringWithoutKeyword(words);
                date = getDate(stringWithoutKeyword, Event.delimiterAt);
                stringWithoutDelimiter = getWithoutDelimiter(stringWithoutKeyword, Event.delimiterAt);
                listArray.add(new Event(stringWithoutDelimiter, date));
                break;
            default:
                listArray.add(new Task(desc));
                break;
        }

        // return the last added, which is the latest
        return Message.ADDED + listArray.get((listArray.size()) - 1) + "\n" +
                "Now you have " + listArray.size() +
                (listArray.size() == 1 ? " task " : " tasks ")
                + "in the list";
    }
}
