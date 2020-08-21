import java.util.List;

public abstract class NumberAction {
    protected static boolean checkIfNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    protected static boolean checkIfValid(int digit, List<Task> tasks) {
        return digit <= tasks.size() && digit > 0;
    }
}
