import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventCommand extends Command {
    private final String TAB = "  ";
    private final String ADD_TASK_TITLE = TAB + " Got it. I've added this task:";
    private String[] input;

    public EventCommand(String[] input) {
        super();
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EventException {
        ArrayList<Task> store = new ArrayList<>();
        int index = 0;
        for(int i = 1; i < input.length; i++) {
            if (input[i].equals("/at")) {
                index = i;
                break;
            }
        }
        if (input.length == 1 || index == 1) { // no description
            throw new EventException(" ☹ OOPS!!! The description of a event cannot be empty.");
        } else if (index == input.length - 1 || index == 0) { //no time
            throw new EventException(" ☹ OOPS!!! The time of a event cannot be empty.");
        }
        String description = "";
        String time = "";
        for(int i = 1; i < index; i++) {
            description = description + input[i] + " ";
        }
        for(int i = index + 1; i < input.length; i++) {
            time = time + input[i] + " ";
        }

        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hhmm");
            date = formatter.parse(time.trim());
        } catch (ParseException e) {
            throw new EventException(" ☹ OOPS!!! The time of a deadline must be in the format of dd/M/yyyy hhmm.");
        }

        Event newTask =  new Event(description.trim(), new SimpleDateFormat("MMM dd yyyy HH:mm").format(date));
        store.add(newTask);
        storage.save(new TaskList(store));

        System.out.println(ADD_TASK_TITLE);
        System.out.println(TAB + "   " + newTask);
        System.out.println(TAB + " Now you have " + store.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}


