package duck;

public class Parser {
    public static Option parseOption(String input) {
        String[] inputSplit = input.split("\\s+");
        Option option = Option.from(inputSplit[0]);
        return option;
    }
}
