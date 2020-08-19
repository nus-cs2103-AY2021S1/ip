import java.security.InvalidParameterException;

public class Parser {

    public static boolean isList(String line) {
        return line.equals("list");
    }

    public static boolean isExit(String line) {
        return line.toLowerCase().contains("bye");
    }

    public static boolean isDone(String line) {
        return line.length() >= 4 && line.substring(0, 4).equals("done");
    }

    public static int taskType(String line) throws InvalidParameterException {
        if (line.length() > 8
            && line.substring(0, 8).equals("deadline")
            && line.contains(" /by ")) {
            return 2;
        }
        else if (line.length() > 5
                && line.substring(0, 5).equals("event")
                && line.contains(" /at ")) {
            return 3;
        }
        else if (line.length() > 4 && line.substring(0, 4).equals("todo")) {
            return 1;
        }
        else throw new InvalidParameterException("Invalid input");
    }

    public static String getName(String line) throws NullPointerException{
        String name;
        if (taskType(line) == 1) {
            name = line.substring(5);
        }
        else if (taskType(line) == 2) {
            name = line.split(" /by ")[0].substring(9);
        }
        else {
            name = line.split(" /at ")[0].substring(6);
        }
        if (name.isEmpty()) {
            throw new NullPointerException("Null Object");
        }
        else return name;
    }

    public static String getTime(String line) throws ArrayIndexOutOfBoundsException{
        try {
            if (taskType(line) == 2) {
                return line.split(" /by ")[1];
            } else {
                return line.split(" /at ")[1];
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

}
