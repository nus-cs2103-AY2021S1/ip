public class Parser {

    public Parser() {
    }

    public static String parse(Planner lst, String msg) throws JimmyException {

        String[] arr = msg.split(" ");
        boolean isLong = arr.length > 1;
        String reply = "";

        if (!isLong) {
            switch (msg) {
            
            case "list":
                reply = lst.toString();
                break;
            
            case "bye":
                reply = "Bye. Hope to see you again soon!";
                break;

            case "todo":
            case "event":
            case "deadline":
                throw new JimmyException(ErrorMessage.EMPTY_DESCRIPTION);

            case "done":
            case "delete":   
                throw new JimmyException(ErrorMessage.EXCESS_DESCRIPTION);

            default:
                throw new JimmyException(ErrorMessage.UNKNOWN_FUNCTION);
            }
        }

        else {
            switch (arr[0]) {

            case "done":
            case "delete":
                if (arr.length > 2) {
                    throw new JimmyException(ErrorMessage.EXCESS_DESCRIPTION);
                }
                int index = Integer.parseInt(arr[1]);

                if (index > lst.getNumTasks()+1 || index < 1) {
                    throw new JimmyException(ErrorMessage.TASK_EXCEED_RANGE);
                }
                if (arr[0].equals("done")) {
                    lst.completeTask(index);
                    Task t = lst.getTask(index);
                    reply = "Nice! I've marked this task as done:" + "\n\t  " + t;
                } else {
                    Task t = lst.getTask(index);
                    lst.del(index);
                    reply = "Noted. I've removed this task:" + "\n\t  " + t + 
                        "\n\tNow you have " + lst.getNumTasks() + " tasks in the list.";
                }
                break;
            
            case "todo":
            case "event":
            case "deadline":
                Task t = null;
                if (arr[0].equals("todo")) {
                    t = new Todo(msg);
                }
                else if (arr[0].equals("event")) {
                    t = new Event(msg);
                }
                else {
                    t = new Deadline(msg);
                }
                lst.addTask(t);
                reply = "Got it. I've added this task:\n\t  " +
                    t + "\n\tNow you have " + lst.getNumTasks() 
                    + " tasks in the list.";
                break;
            
            default:
                throw new JimmyException(ErrorMessage.UNKNOWN_FUNCTION);
            }
        }
        return reply;
    }

}