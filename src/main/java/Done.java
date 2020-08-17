import java.util.List;

public class Done {

    public static boolean isValid(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void handleDone(String input, List<Task> tasks) {
        try {
            int len = input.length();
            if (len >= 6 && isValid(input.substring(5, len))) {
                int digit = Integer.parseInt(input.substring(5, len));

                if (digit <= tasks.size() && digit > 0) {
                    Task current = tasks.get(digit - 1);
                    if (current.checkIfDone()) {
                        throw new DukeException("Task number does not exist in the list");
                    } else {
                        current.markAsDone();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + current);
                    }
                } else {
                    throw new DukeException("Task number does not exist in the list");
                }
            } else {
                throw new DukeException("Input format is invalid");
            }
        } catch (DukeException e) {
            System.out.println("    " + e.getMessage());
        }
    }
}
