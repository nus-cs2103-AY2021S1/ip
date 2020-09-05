package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.Comparator;
import java.util.LinkedList;

public class CommandSort implements Command {
    private String comparator;
    private String sortedListMsg = "";

    public CommandSort(String comparator) {
        this.comparator = comparator;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws Exception {
        LinkedList<Task> list = tasks.getList();
        Comparator<Task> taskComparator;

        switch (comparator) {
            case "name": {
                taskComparator = new Comparator<>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        return (o1.getDescription().compareTo(o2.getDescription()));
                    }
                };
                break;
            }
            case "kind": {
                taskComparator = new Comparator<>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        return o1.getPriority() - o2.getPriority();
                    }
                };
                break;
            }
            case "date": {
                taskComparator = new Comparator<>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        if (o1 instanceof Deadline && o2 instanceof Deadline) {
                            if (((Deadline) o1).hasDate() && ((Deadline) o2).hasDate()) {
                                return ((Deadline) o1).getByDate().compareTo(((Deadline) o2).getByDate());
                            } else if (((Deadline) o1).hasDate()) {
                                return -1;
                            } else if (((Deadline) o2).hasDate()) {
                                return 1;
                            } else return ((Deadline) o1).getByString().compareTo(((Deadline) o2).getByString());
                        } else if (o1 instanceof Deadline) {
                            return -1;
                        } else if (o2 instanceof Deadline) {
                            return 1;
                        } else if (o1 instanceof Event) {
                            if (o2 instanceof Event) {
                                if (((Event) o1).hasDate()) {
                                    if (((Event) o2).hasDate()) {
                                        return ((Event) o1).getAtDate().compareTo(((Event) o2).getAtDate());
                                    } else return -1;
                                } else return ((Event) o1).getAtString().compareTo(((Event) o2).getAtString());
                            } else return -1;
                        } else if (o2 instanceof Event) {
                            return 1;
                        } else return o1.getDescription().compareTo(o2.getDescription());
                    }
                };
                break;
            }
            default:
                sortedListMsg += "Oops! You can only sort by name, kind or date!";
                throw new Exception("Illegal comparator!");
        }

        list.sort(taskComparator);
        ui.printList(list);
        if (list.size() == 0) {
            sortedListMsg += "You have no tasks in your list now!\nType todo, event or deadline to add some!\n";
        } else {
            sortedListMsg += "Here are the tasks in your list, sorted by " + comparator + ":\n";
            for (int i = 1; i <= list.size(); i++) {
                sortedListMsg += (i + "." + list.get(i - 1) + "\n");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getMessage() {
        return sortedListMsg;
    }

}
