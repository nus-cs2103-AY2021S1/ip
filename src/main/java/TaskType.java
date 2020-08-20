import java.lang.annotation.Inherited;

public enum TaskType {
    TODO, EVENT, DEADLINE;

    public String toString(){
        switch(this){
            case TODO :
                return "todo";
            case EVENT :
                return "event";
            case DEADLINE :
                return "deadline";
        }
        return null;
    }

    public static boolean isTask(String taskType) {
        return taskType.equals(TODO.toString()) || taskType.equals(EVENT.toString()) || taskType.equals(DEADLINE.toString());
    }
}



