public class InputParser {

    public static boolean isDone(String input) {
        String[] inputArr = input.split(" ");
        return inputArr.length == 2
                && input.substring(0, 4).equals("done")
                && isNumber(input.substring(5,6));
    }

    public static boolean isDelete(String input) {
        String[] inputArr = input.split(" ");
        return inputArr.length == 2
                && input.substring(0, 6).equals("delete")
                && isNumber(input.substring(7,8));
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean correctInputFormat(String input) {
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1) {
            return false;
        }
        //correct todo format
        boolean todoBool = inputArr.length == 2 && inputArr[0].equals("todo");

        String taskWithDate = inputArr[1];
        String[] taskAndDateArr = taskWithDate.split(" /");
        //correct deadline format
        boolean deadlineBool = taskAndDateArr.length == 2 && inputArr[0].equals("deadline");

        //correct event format
        boolean eventBool = taskAndDateArr.length == 2 && inputArr[0].equals("event");

        return todoBool || deadlineBool || eventBool;
    }

    public static Task parseTask(String[] arr) {
        String taskCode = arr[0];
        String isDoneStr = arr[1];
        boolean isDone = isDoneStr.equals("1") ? true : false;
        String task = arr[2];

        //if toDo item
        if (taskCode.equals("T")) {
            ToDos todo = new ToDos(task, isDone);
            return todo;
        //if deadline item
        } else if (taskCode.equals("D")) {
            String date = arr[3];
            Deadlines deadline = new Deadlines(task, date, isDone);
            return deadline;
        //if events item
        } else {
            String time = arr[3];
            Events event = new Events(task, time, isDone);
            return event;
        }
    }

}
