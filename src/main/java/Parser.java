public class Parser {

    public static boolean parseBye(String input){
        return input.equals(TaskElement.BYE.label);

    }

    public static boolean parseList(String input){
        return input.equals(TaskElement.LIST.label);

    }

    public static boolean parseDone(String input){
        return input.split(" ")[0].equals(TaskElement.DONE.label);
    }

    public static boolean parseToDo(String input){
        return input.split(" ")[0].equals(TaskElement.TODO.label);
    }

    public static boolean parseDeadline(String input){
        return input.split(" ")[0].equals(TaskElement.DEADLINE.label);
    }

    public static boolean parseEvent(String input){
        return input.split(" ")[0].equals(TaskElement.EVENT.label);
    }

    public static boolean parseDelete(String input){
        return input.split(" ")[0].equals(TaskElement.DELETE.label);
    }

    public static String stringBuilder(String[] arr, int start, int end){
        String store = "";
        for (int i = start; i <= end; i++) {
            if(i == end){
                store += arr[i];
            } else {
                store += arr[i] + " ";
            }

        }
        return store;
    }


}
