public class Parser {

    public final static String BYE = "bye";
    public final static String LIST = "list";
    public final static String DONE = "done";
    public final static String DEL = "delete";
    public final static String DONE_VALID = "done 1";
    public final static String DEL_VALID = "delete 1";
    public final static String TODO = "todo";
    public final static String TODO_VALIDATION = "todo ";
    public final static String EVT = "event";
    public final static String EVT_VALIDATION = "event ";
    public final static String DDL = "deadline";
    public final static String DDL_VALIDATION = "deadline ";
    public final static String EMPTY = " ";
    public final static String BYORAT = "/by ";

    public Parser(){}

    public boolean isBye(String input){
        return input.equals(BYE);
    }

    public boolean isList(String input){
        return input.equals(LIST);
    }

    public boolean isDone(String input){
        return input.contains(DONE);
    }

    public boolean isDelete(String input){
        return input.contains(DEL);
    }

    public boolean isTodo(String input){
        return input.contains(TODO);
    }

    public boolean isValidTodo(String input){
        return input.length() > TODO_VALIDATION.length();
    }

    public boolean isEvent(String input){
        return input.contains(EVT);
    }

    public boolean isValidEvent(String input){
        return input.length() > EVT_VALIDATION.length();
    }

    public boolean isDeadline(String input){
        return input.contains(DDL);
    }

    public boolean isValidDeadline(String input){
        return input.length() > DDL_VALIDATION.length();
    }

    public boolean isEmptyDescription(String input){
        return input.equals(EMPTY);
    }


}
