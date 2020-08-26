public class Parser {
    public static Command parse(String input) {
        String checker;
        if (input.length() > 5) {
            checker = input.substring(0, 4);
        } else {
            checker = "nothing";
        }
        if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if(checker.equals("done")) {
            int num = Character.getNumericValue(input.charAt(5));
            return new DoneCommand(input, num);
        } else if (checker.equals("dele")) {
            int num = Character.getNumericValue(input.charAt(7));
            return new DeleteCommand(input, num);
        } else {
            return new AddCommand(input);
        }
    }
}
