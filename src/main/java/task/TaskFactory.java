package task;

import java.lang.StringBuilder;

public class TaskFactory {
    private static String concatenateString(String[] commands, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < end; i++) {
            builder.append(commands[i] + " ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static int getBreakIndex(String[] commands, String breakString) {
        int breakIndex = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals(breakString)) {
                breakIndex = i;
            }
        }
        return breakIndex;
    }

    public static Task createToDo(String[] commands) {
        String taskString = concatenateString(commands, 1, commands.length);
        return new ToDo(taskString, false);
    }

    public static Deadline createDeadline(String[] commands) {
        int breakIndex = getBreakIndex(commands, Deadline.DEADLINE_BREAK);
        String taskString = concatenateString(commands, 1, breakIndex);
        String datetimeString = concatenateString(commands, breakIndex + 1, commands.length);
        return new Deadline(taskString, false, datetimeString);
    }
}
