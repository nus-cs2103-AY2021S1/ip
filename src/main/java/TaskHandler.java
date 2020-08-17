public class TaskHandler {
    // input a line and check what it is and act accordingly

    public Task.Type getType(String input) {
        Task.Type type;
        String typeString = input.split(" ", 2)[0];
        switch (typeString) {
            case "todo": type = Task.Type.TODO; break;
            case "deadline" : type = Task.Type.DEADLINE; break;
            case "event": type = Task.Type.EVENT; break;
            default: type = Task.Type.UNKNOWN;
        }
        return type;
    }

    public String getTodo(String input) {
        return input.split(" ", 2)[1];
    }

    public boolean checkDeadline(String input) {
        return input.split(" /by ", 2).length == 2;
    }

    public String getDTask(String input) {
        return input.split(" ", 2)[1].split(" /by ", 2)[0];
    }

    public String getDTime(String input) {
        return input.split(" ", 2)[1].split(" /by ", 2)[1];
    }

    public boolean checkEvent(String input) {
        return input.split(" /at ", 2).length == 2;
    }

    public String getETask(String input) {
        return input.split(" ", 2)[1].split(" /at ", 2)[0];
    }

    public String getETime(String input) {
        return input.split(" ", 2)[1].split(" /at ", 2)[1];
    }

    public boolean isValid(String input) {
        String[] parsed = input.split(" ", 2);
        String key = parsed[0];
        return key.equals("todo") || key.equals("deadline") || key.equals("event");
    }

    public boolean isList(String input) {
        String[] parsed = input.split(" ", 2);
        return parsed[0].equals("list") && parsed.length == 1;
    }

    public boolean isDone(String input) {
        String[] parsed = input.split(" ", 2);
        return parsed[0].equals("done") && parsed.length == 2;
    }

    public int getNumber(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]);
    }

    public boolean isBye(String input){
        String[] parsed = input.split(" ", 2);
        return parsed[0].equals("bye") && parsed.length == 1;
    }
}
