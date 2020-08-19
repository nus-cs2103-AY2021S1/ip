import java.util.ArrayList;

public class Userinput {
    private boolean terminate;
    ArrayList<Task> tasks = new ArrayList<>();

    public Userinput() {
        this.terminate = false;
    }

    String getDukeResponse(String input) {
        String[] words = input.split(" ");
        String taskGrammar = "task";
        if (this.tasks.size() > 0) {
            taskGrammar = "tasks";
        }

        if (words[0].equals("deadline")) {
            //deadline cases
            String deadlineTask = "";
            String deadlineDate = "";
            int byPosition = 0;
            for (int i = 1;  i < words.length; i++) {
                if (!words[i].equals("/by")) {
                    deadlineTask = deadlineTask + words[i] + " ";
                } else {
                    byPosition = i;
                    break;
                }
            }

            for (int k = byPosition + 1; k < words.length; k++) {
                if (k != words.length-1) {
                    deadlineDate = deadlineDate + words[k] + " ";
                } else {
                    deadlineDate = deadlineDate + words[k];
                }
            }
            Deadline newDeadline = new Deadline(deadlineTask,deadlineDate);
            this.tasks.add(newDeadline);
            String addNewDeadlineString = "____________________________________________________________\n" +
                    "     Got it. I've added this task: \n" +
                    "       " + newDeadline.toString() + "\n" +
                    "     Now you have " + this.tasks.size() + " "+ taskGrammar + " in the list.\n" +
                    "____________________________________________________________\n";
            return addNewDeadlineString;

        }  else if (words[0].equals("todo")) {
            //todo case
            String todoTask = "";
            for (int i = 1; i < words.length; i++) {
                todoTask = todoTask + words[i] + " ";
            }
            ToDo newToDo = new ToDo(todoTask);
            this.tasks.add(newToDo);
            String addNewToDo = "____________________________________________________________\n" +
                    "     Got it. I've added this task: \n" +
                    "       " + newToDo.toString() + "\n" +
                    "     Now you have " + this.tasks.size() + " "+ taskGrammar + " in the list.\n" +
                    "____________________________________________________________\n";
            return addNewToDo;

        } else if (words[0].equals("event")){
            String eventTask = "";
            String eventDate = "";
            int atPosition = 0;
            for (int i = 1;  i < words.length; i++) {
                if (!words[i].equals("/at")) {
                    eventTask = eventTask + words[i] + " ";
                } else {
                    atPosition = i;
                    break;
                }
            }

            for (int k = atPosition + 1; k < words.length; k++) {
                if (k != words.length-1) {
                    eventDate = eventDate + words[k] + " ";
                } else {
                    eventDate = eventDate + words[k];
                }
            }
            Event newEvent = new Event(eventTask,eventDate);
            this.tasks.add(newEvent);
            String addNewEventString = "____________________________________________________________\n" +
                    "     Got it. I've added this task: \n" +
                    "       " + newEvent.toString() + "\n" +
                    "     Now you have " + this.tasks.size() + " "+ taskGrammar + " in the list.\n" +
                    "____________________________________________________________\n";
            return addNewEventString;

        } else if (words[0].equals("done")) {
            //done cases
            int number = Integer.parseInt(words[1])-1;
            return this.tasks.get(number).markAsDone();
        } else {
            //normal list,bye,blah,normal task(not event, deadline or T0d0) cases
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
        }
    }

    boolean getTerminate() {
        return this.terminate;
    }

}
