package duke;
public class TaskListError extends IndexOutOfBoundsException {
    
    private static String errorType1 = "Accessing out of bounds index in TaskList " +
            "You tried to access index %s The list is of size %s";
    
    private static String errorType2 = "Deleting out of bounds index in TaskList " +
            "You tried to delete index %s The list is of size %s";
    
    private static final String INVALID_ERROR_MESSAGE = "Invalid Error Type";
    
    private int errorType;
    private int indexAccessed;
    private int sizeOfList;
    
    public TaskListError(int errorType, int indexAccessed, int sizeOfList) {
        assert isValidErrorType() : INVALID_ERROR_MESSAGE;
        this.errorType = errorType;
        this.indexAccessed = indexAccessed;
        this.sizeOfList = sizeOfList;
    }
    
    public String getDetails() {
        if (isErrorType1()) {
            return getError1Message();
        } else if (isErrorType2()) {
            return getError2Message();
        }
        return INVALID_ERROR_MESSAGE;
    }
    
    private boolean isErrorType1() {
        return errorType == 1;
    }
    
    private String getError1Message() {
        return String.format(errorType1,indexAccessed,sizeOfList);
    }
    
    private boolean isErrorType2() {
        return errorType == 2;
    }
    
    private String getError2Message() {
        return String.format(errorType2,indexAccessed,sizeOfList);
    }
    
    private boolean isValidErrorType() {
        return isErrorType1() || isErrorType2();
    }
}
