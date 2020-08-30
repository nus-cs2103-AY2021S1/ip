package duke;
public class TaskListError extends IndexOutOfBoundsException {
    
    private static String errorType1 = "Accessing out of bounds index in TaskList " +
            "You tried to access index %s The list is of size %s";
    
    private static String errorType2 = "Deleting out of bounds index in TaskList " +
            "You tried to delete index %s The list is of size %s";
    
    private int errorType;
    private int indexAccessed;
    private int sizeOfList;
    
    public TaskListError(int errorType, int indexAccessed, int sizeOfList) {
        this.errorType = errorType;
        this.indexAccessed = indexAccessed;
        this.sizeOfList = sizeOfList;
    }
    
    public String getDetails() {
        if (errorType == 1) {
            return String.format(errorType1,indexAccessed,sizeOfList);
        } else if (errorType == 2) {
            return String.format(errorType2,indexAccessed,sizeOfList);
        }
        return "Unknown error";
    }
}
