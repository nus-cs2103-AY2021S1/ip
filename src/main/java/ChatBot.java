import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello I'm Duke!\nWhat can I do for you?");
        boolean ended = false;
        while(!ended){
            String query = scanner.nextLine();
            String resp = ChatBot.response(query);
            ended = ChatBot.isEnd(query);
            if(!ended){
                System.out.println(resp);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String response(String query){
        String[] splitQuery = query.split("\\s+");
        String command = splitQuery[0].toLowerCase();
        String[] cRemoved = removeCommandString(splitQuery);
        switch (command){
            case "done":
                if(splitQuery.length != 2){
                    return "Error: Usage of command 'done' should be done as follows: 'done <task number>'";
                }
                return markedAsDone(splitQuery[1]);
            case "clear":
                return "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
            case "list":
                return listOfTasks();
            case "todo":
                String editedQ = concatenateStrArr(cRemoved);
                Task toDo = ChatBot.addToDo(editedQ);
                return taskAdded(toDo);
            case "deadline":
                String title = getTitle(cRemoved);
                String preposition = getPreposition(cRemoved);
                String dateTime = getDateTime(cRemoved);
                Task deadline = ChatBot.addDeadline(title,preposition,dateTime);
                return taskAdded(deadline);
            case "event":
                String ttle = getTitle(cRemoved);
                String ppstn = getPreposition(cRemoved);
                String dT = getDateTime(cRemoved);
                Task event = ChatBot.addEvent(ttle,ppstn,dT);
                return taskAdded(event);
            default:
                return "Error: Unknown command. Type 'help' to get the list of commands";
        }
    }

    private static String listOfTasks(){
        StringBuilder acc = new StringBuilder();
        int i = 0;
        for (Task t: taskList){
            i++;
            acc.append(String.format("%d. %s\n", i, t));
        }
        return acc.toString();
    }

    private static Task addToDo(String query){
        Task newTask = new ToDo(query);
        taskList.add(newTask);
        return newTask;
    }

    private static Task addDeadline(String title, String preposition, String dateTime){
        Task newTask = new Deadline(title,preposition,dateTime);
        taskList.add(newTask);
        return newTask;
    }

    private static Task addEvent(String title, String preposition, String dateTime){
        Task newTask = new Event(title,preposition,dateTime);
        taskList.add(newTask);
        return newTask;
    }

    private static String markedAsDone(String listIndex){
        int idx = Integer.parseInt(listIndex) - 1;
        if (idx<0||idx>taskList.size()){
            return "Error: Please enter a number that is in range of the task numbers";
        } else{
            Task curr = taskList.get(idx);
            if(curr.isDone()){
                return "Error: This task has already been marked as done";
            }
            curr.markDone();
            return String.format("Nice I have marked this task as done:\n\t%s\nNow you have %d tasks left",curr,taskList.size());
        }
    }

    private static String taskAdded(Task task){
        return "Got it. I've added this task:\n\t" + task.toString() + String.format("\nNow you have %d tasks in the list",taskList.size());
    }

    private static String[] removeCommandString(String[] splitQuery){
        splitQuery[0] = "";
        return splitQuery;
    }

    private static String concatenateStrArr(String[] strArr){
        StringBuilder acc = new StringBuilder();
        for (String s: strArr) {
            if(!s.equals("")) {
                acc.append(" ").append(s);
            }
        }
        return acc.toString();
    }

    private static String getTitle(String[] splitQuery){
        StringBuilder accTaskTitle = new StringBuilder();
        int i = 1;
        while(!splitQuery[i].startsWith("/")){
            accTaskTitle.append(" ").append(splitQuery[i]);
            i++;
        }
        return accTaskTitle.toString();
    }

    private static String getPreposition(String[] splitQuery){
        for (String s:splitQuery) {
            if(s.startsWith("/")){
                return s.substring(1);
            }
        }
        return "";
    }

    private static String getDateTime(String[] splitQuery){
        StringBuilder accTaskDateTime = new StringBuilder();
        int i = 0;
        while(!splitQuery[i].startsWith("/")){
            i++;
        }
        i++;
        while(i < splitQuery.length){
            accTaskDateTime.append(" ").append(splitQuery[i]);
            i++;
        }
        return accTaskDateTime.toString();
    }

    private static boolean isEnd(String query){
        String editedQuery = query.stripLeading().stripTrailing();
        return "bye".equalsIgnoreCase(editedQuery);
    }
}
