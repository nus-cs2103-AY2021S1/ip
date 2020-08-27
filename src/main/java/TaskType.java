import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TaskType {
    DEADLINE("deadline") {
        @Override
        public void addToTasks(List<Task> tasks, String taskName, boolean taskIsDone, String taskDate) throws DukeException {
            if (taskName.isBlank()) {
                throw new NullTaskNameException("deadline");
            }
            if (taskDate.isBlank()) {
                throw new NullTaskDateException("deadline");
            }
            Task task = new Deadline(taskName, taskDate);
            if (taskIsDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }

        @Override
        public String generateSaveLine(boolean taskIsDone, String taskName, String taskDate) {
            return String.format("deadline:%s:%s:%s\n", taskIsDone ? "1" : "0", taskName, taskDate);
        }
    },
    EVENT("event") {
        @Override
        public void addToTasks(List<Task> tasks, String taskName, boolean taskIsDone, String taskDate) throws DukeException {
            if (taskName.isBlank()) {
                throw new NullTaskNameException("event");
            }
            if (taskDate.isBlank()) {
                throw new NullTaskDateException("event");
            }
            Task task = new Event(taskName, taskDate);
            if (taskIsDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }

        @Override
        public String generateSaveLine(boolean taskIsDone, String taskName, String taskDate) {
            return String.format("event:%s:%s:%s\n", taskIsDone ? "1" : "0", taskName, taskDate);
        }
    },
    TODO("todo") {
        @Override
        public void addToTasks(List<Task> tasks, String taskName, boolean taskIsDone, String taskDate) throws DukeException {
            if (taskName.isBlank()) {
                throw new NullTaskNameException("todo");
            }
            Task task = new ToDo(taskName);
            if (taskIsDone) {
                task.markAsDone();
            }
            System.out.println(task.isDone);
            tasks.add(task);
        }

        @Override
        public String generateSaveLine(boolean taskIsDone, String taskName, String taskDate) {
            return String.format("todo:%s:%s\n", taskIsDone ? "1" : "0", taskName);
        }
    };

    private static final Map<String, TaskType> nameToValueMap = new HashMap<>();
    private final String lowerCase;

    TaskType(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public String getLowerCase() {
        return lowerCase;
    }

    static {
        for (TaskType type : EnumSet.allOf(TaskType.class)) {
            nameToValueMap.put(type.getLowerCase(), type);
        }
    }

    public static boolean isMember(String name) {
        return nameToValueMap.containsKey(name);
    }

    public static TaskType getTaskType(String lowerCase) {
        return nameToValueMap.get(lowerCase);
    }

    public abstract void addToTasks(List<Task> tasks, String taskName, boolean taskIsDone, String taskDate) throws DukeException;
    public abstract String generateSaveLine(boolean taskIsDone, String taskName, String taskDate);
}
