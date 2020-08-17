import java.util.List;

public class NumberAction {
    public static boolean checkIfNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static boolean checkIfValid(int digit, List<Task> tasks) {
        return digit <= tasks.size() && digit > 0;
    }
}
