public class DukeEmptyDescException extends DukeException {

    private final String type;

    DukeEmptyDescException(TaskType taskType) {
        switch (taskType) {
        case TODO:
            type = "todo";
            break;
        case EVENT:
            type = "event";
            break;
        case DEADLINE:
            type = "deadline";
            break;
        default:
            type = "";
            break;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format("You can't have an empty description for this %s task silly!\n",
                type);
    }

}
