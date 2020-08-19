import java.util.ArrayList;

public class Userinput {
    private boolean terminate;
    ArrayList<Task> tasks = new ArrayList<>();

    public Userinput() {
        this.terminate = false;
    }

    String getDukeResponse(String input) {
        String[] words = input.split(" ");
        if (!words[0].equals("done")) {
            String blahResponse = "____________________________________________________________\n" +
                    "blah\n" +
                    "____________________________________________________________";
            String byeResponse = "____________________________________________________________\n" +
                    "Bye. Hope to see you again soon!\n" +
                    "____________________________________________________________";
            if (input.equals("list")) {
                String listResponse = "____________________________________________________________\n" +
                        "Here are the tasks in your list:\n";
                for (int i = 0; i < this.tasks.size(); i++) {
                    String number = Integer.toString(1 + i);
                    listResponse = listResponse + number + ". " + this.tasks.get(i).toString() + "\n";
                }
                listResponse = listResponse + "____________________________________________________________";
                return listResponse;
            } else if (input.equals("blah")) {
                return blahResponse;
            } else if (input.equals("bye")) {
                this.terminate = true;
                return byeResponse;
            } else {
                this.tasks.add(new Task(input));
                return "____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n";
            }
        } else {
            int number = Integer.parseInt(words[1]);
            return this.tasks.get(number).markAsDone();
        }
    }

    boolean getTerminate() {
        return this.terminate;
    }

}
