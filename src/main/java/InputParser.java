public class InputParser {

    public static boolean isDone(String input) {
        return input.length() == 6
                && input.substring(0, 4).equals("done")
                && isNumber(input.substring(5,6));
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean correctInputFormat(String input) {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            return false;
        }
        //correct todo format
        boolean todoBool = inputArr.length == 2 && inputArr[0].equals("todo");

        String taskWithDate = inputArr[1];
        String[] taskAndDateArr = taskWithDate.split(" /");
        //correct deadline format
        boolean deadlineBool = taskAndDateArr.length == 2 && inputArr[0].equals("deadline");

        //correct event format
        boolean eventBool = taskAndDateArr.length == 2 && inputArr[0].equals("event");

        return todoBool || deadlineBool || eventBool;
    }
}
