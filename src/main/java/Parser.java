public class Parser {

    public static boolean isBye(String input) {
        return input.equals(TaskElement.BYE.label);

    }

    public static boolean isList(String input) {
        return input.equals(TaskElement.LIST.label);

    }

    public static boolean isDone(String input) {
        return input.split(" ")[0].equals(TaskElement.DONE.label);
    }


    public static boolean isToDo(String input) {
        return input.split(" ")[0].equals(TaskElement.TODO.label);
    }

    public static boolean isDeadline(String input) {
        return input.split(" ")[0].equals(TaskElement.DEADLINE.label);
    }

    public static boolean isEvent(String input) {
        return input.split(" ")[0].equals(TaskElement.EVENT.label);
    }

    public static boolean isDelete(String input) {
        return input.split(" ")[0].equals(TaskElement.DELETE.label);
    }

    public static String stringBuilder(String[] arr, int start, int end) {
        String store = "";
        for (int i = start; i <= end; i++) {
            if (i == end) {
                store += arr[i];
            } else {
                store += arr[i] + " ";
            }

        }
        return store;
    }

    public static boolean isFind(String input){
        return input.split(" ")[0].equals(TaskElement.FIND.label);
    }


}
