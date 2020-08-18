import java.util.Arrays;

public class StringProcessor {

    private String rawInput;

//    StringProcessor(String rawInput) {
//        this.rawInput = rawInput;
//    }

    public static Task stringProcessor(String rawInput) {
        String[] splitString = rawInput.split(" ");
        int splitStringLength = splitString.length;

        if (splitString[0].equals("todo")) {
            String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
            return new Todo(String.join(" ", taskStringArray));
        } else if (splitString[0].equals("deadline")) {
            String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
            String toSplit = String.join(" ", taskStringArray);
            int indexOfSlash = toSplit.indexOf("/");
            int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
            return new Deadline(toSplit.substring(0, indexOfSlash - 1),
                    toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                    toSplit.substring(indexOfNextWord + 1));

        } else if (splitString[0].equals("event")) {
            String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
            String toSplit = String.join(" ", taskStringArray);
            int indexOfSlash = toSplit.indexOf("/");
            int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
            return new Event(toSplit.substring(0, indexOfSlash - 1),
                    toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                    toSplit.substring(indexOfNextWord + 1));
        } else {
            return null;
        }
    }
}
