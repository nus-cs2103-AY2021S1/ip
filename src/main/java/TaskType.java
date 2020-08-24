public enum TaskType {
    DEADLINE("ID_D"),
    EVENT("ID_E"),
    TODO("ID_T");

    private static final String IDENTIFIER_DELIMITER = " \\| ";

    private final String id;

    TaskType(String id) {
        this.id = id;
    }

    public static String appendIdentifier(String storageString, TaskType taskType) throws TaskTypeDecodeException {
        return taskType.id + IDENTIFIER_DELIMITER + storageString;
    }

    public static TaskType decodeTaskType(String storageString) throws TaskTypeDecodeException {
        try {
            String identifier = storageString.split(IDENTIFIER_DELIMITER)[0];
            switch (identifier) {
            case Deadline.STORE_DEADLINE:
                return TaskType.DEADLINE;
            case Event.STORE_EVENT:
                return TaskType.EVENT;
            case Todo.STORE_TODO:
                return TaskType.TODO;
            default:
                throw new TaskTypeDecodeException("Unknown task type");
            }
        } catch (IndexOutOfBoundsException e) {
             throw new TaskTypeDecodeException("Invalid storage string.");
        }
    }

    public static String getStorageLine(String input, TaskType tasktype) throws TaskTypeDecodeException {
        int start = tasktype.toString().length() + IDENTIFIER_DELIMITER.length();
        try {
            StringBuilder string = new StringBuilder();
            for (int i = start; i < input.length(); i++) {
                string.append(input.charAt(i));
            }
            return string.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskTypeDecodeException("Storage string has been malformed.");
        }
    }
}
