package duke;

import task.*;

public class Duke {
    private static final String FILEPATH = "duke.txt";
    private TaskList taskList;

    private String executeBye(){
        return "Bye. Hope to see you again soon!";
    }

    private String executeList(){
        StringBuilder replyBuilder = new StringBuilder();

        for(int i = 0; i < taskList.size(); i++){
            replyBuilder.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return replyBuilder.toString();
    }

    private String executeRemove(String input){
        String indexStr = input.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(indexStr) - 1;
        Task t = taskList.remove(index);
        String reply = "Noted. I've removed this task:\n"
                +"\t" + t + "\n"
                + "\t" + "Now you have " + taskList.size() + " tasks in the list.";
        return reply;
    }

    private String executeDone(String input){
        String indexStr = input.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(indexStr) - 1;
        taskList.get(index).setDone();
        return "Nice! I've marked this task as done:\n"
                + taskList.get(index);
    }

    private String executeFind(String[] command){
        TaskList foundList = taskList.find(command[1]);
        StringBuilder replyBuilder = new StringBuilder();

        for(int i = 0; i < foundList.size(); i++){
            replyBuilder.append(i + 1).append(". ").append(foundList.get(i)).append("\n");
        }
        return replyBuilder.toString();
    }

    private String executeTodo(String[] command){
        try {
            Task newTask = new Todo(command[1]);
            taskList.add(newTask);
            return "Got it. I've added this task:\n"
                    + "\t" + newTask;
        }
        catch(EmptyStringException e){
            return "Todo cannot be empty.";
        }
    }

    private String executeDeadline(String[] commands){
        try {
            Task newTask = new Deadline(commands[1]);
            taskList.add(newTask);
            return "Got it. I've added this task:\n"
                    + "\t" + newTask;
        }
        catch(EmptyStringException e){
            return "Deadline cannot be empty.";
        }
    }
    private String executeEvent(String[] commands){
        try {
            Task newTask = new Event(commands[1]);
            taskList.add(newTask);
            return "Got it. I've added this task:\n"
                    + "\t" + newTask;
        }
        catch(EmptyStringException e){
            return "Event cannot be empty.";
        }
    }
    private String executeNothing(){
        return "D: I did not understand.";
    }
    /**
     * Calculates a response from the given command.
     * @return A response.
     */
    public String getResponse(String input) {
        String reply = "";
        try {
            String[] commands = Parser.parseCommand(input);
            String command_1 = commands[0];
            switch(command_1){
            case "bye":
                reply = executeBye();
                break;
            case "list":
                reply = executeList();
                break;
            case "remove":
                reply = executeRemove(input);
                Storage.saveTasks(FILEPATH, taskList);
                break;
            case "done":
                reply = executeDone(input);
                Storage.saveTasks(FILEPATH, taskList);
                break;
            case "find":
                reply = executeFind(commands);
                break;
            case "todo":
                reply = executeTodo(commands);
                Storage.saveTasks(FILEPATH, taskList);
                break;
            case "deadline":
                reply = executeDeadline(commands);
                Storage.saveTasks(FILEPATH, taskList);
                break;
            case "event":
                reply = executeEvent(commands);
                Storage.saveTasks(FILEPATH, taskList);
                break;
            default:
                return executeNothing();
            }
            return reply;
        }
        catch(Exception e){
            reply = reply + "\n" + e.getMessage();
            return reply;
        }
    }

    public Duke() {
        taskList = Storage.loadTasks(FILEPATH);
    }
}