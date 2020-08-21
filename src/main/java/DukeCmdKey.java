import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum DukeCmdKey {

    BYE {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            // Nothing to do
            return;
        }
    },

    LIST {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            // Print all tasks in tasklist
            int i = 0;
            for (Task item : taskList) {
                System.out.println(++i + ". " + item.toString());
            }
        }
    },

    DONE {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            // Mark task as done
            try {
                int taskIndex = Integer.parseInt(cmdParam.trim()) - 1;
                Task task = taskList.get(taskIndex);
                task.setCompleted(true);
                System.out.println("\t# Done: " + task.toString());
            } catch(NumberFormatException e) {
                System.out.println("Format Error: done {index}");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index Error: done {index}");
            }
        }
    },

    DELETE {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            // Remove task from tasklist
            try {
                int taskIndex = Integer.parseInt(cmdParam.trim()) - 1;
                Task task = taskList.remove(taskIndex);
                System.out.println("\t- Delete: " + task.toString());
            } catch(NumberFormatException e) {
                System.out.println("Format Error: delete {index}");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index Error: delete {index}");
            }
        }
    },

    TODO {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            // Add todoTask to tasklist
            if (!cmdParam.isBlank()) {
                Task task = new ToDo(cmdParam.trim());
                taskList.add(task);
                System.out.println("\t+ Add ToDo: " + task.toString());
            } else {
                System.out.println("Format Error: todo {description}");
            }
        }
    },

    DEADLINE {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            Pattern p = Pattern.compile("^(.+)\\/by(.+)$");
            Matcher m = p.matcher(cmdParam);
            if (m.matches() && !m.group(1).isBlank() && !m.group(2).isBlank()) {
                Task task = new Deadline(m.group(1).trim(), m.group(2).trim());
                taskList.add(task);
                System.out.println("\t+ Add Deadline: " + task.toString());
            } else {
                System.out.println("Format Error: deadline {description} /by {deadline}");
            }
        }
    },

    EVENT {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            Pattern p = Pattern.compile("^(.+)\\/at(.+)$");
            Matcher m = p.matcher(cmdParam);
            if (m.matches() && !m.group(1).isBlank() && !m.group(2).isBlank()) {
                Task task = new Event(m.group(1).trim(), m.group(2).trim());
                taskList.add(task);
                System.out.println("\t+ Add Event: " + task.toString());
            } else {
                System.out.println("Format Error: Event {description} /at {time}");
            }
        }
    },

    UNDEFINED {
        @Override
        public void execute(List<Task> taskList, String cmdParam) {
            // Nothing to do
            System.out.println("Unknown Command!");
            return;
        }
    };

    /**
     * Executes the DukeCmd
     *
     * @param taskList The Duke Task List
     * @param cmdParam Command parameters (If required)
     */
    public abstract void execute(List<Task> taskList, String cmdParam);

    /**
     * Convert a DukeCmd command (String) to DukeCmdKey enum
     *
     * @param command The DukeCmd command
     * @return The corresponding DukeCmdKey
     */
    public static DukeCmdKey fromString(String command) {
        String s = command.toUpperCase();
        for (DukeCmdKey dukeCmdKey : DukeCmdKey.values()) {
            if (s.equals(dukeCmdKey.toString())) return dukeCmdKey;
        }
        return DukeCmdKey.UNDEFINED;
    }

}
