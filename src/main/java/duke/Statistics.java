package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The Statistics object contains a range of statistics regarding the tasks in the list.
 */
public class Statistics {
    private ArrayList<Double> statistics;
    private double numOfTodos = 0;
    private double numOfDoneTodos = 0;
    private double numOfEvents = 0;
    private double numOfDoneEvents = 0;
    private double numOfDeadlines = 0;
    private double numOfDoneDeadlines = 0;
    private double numOfDoneTasks = 0;
    private int percentageOfDoneTodos;
    private int percentageOfDoneDeadlines;
    private int percentageOfDoneEvents;
    private int percentageOfDoneTasks;

    /**
     * Initializes the Statistics object.
     *
     * @param tasks The current list of tasks.
     */
    public Statistics(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            switch (task.getType()) {
            case "todo":
                numOfTodos++;
                if (task.getDoneStatus()) {
                    numOfDoneTasks++;
                    numOfDoneTodos++;
                }
                break;
            case "deadline":
                numOfDeadlines++;
                if (task.getDoneStatus()) {
                    numOfDoneTasks++;
                    numOfDoneDeadlines++;
                }
                break;
            case "event":
                numOfEvents++;
                if (task.getDoneStatus()) {
                    numOfDoneTasks++;
                    numOfDoneEvents++;
                }
                break;
            }
        }

        percentageOfDoneTodos = (int) (numOfDoneTodos / numOfTodos * 100);
        percentageOfDoneDeadlines = (int) (numOfDoneDeadlines / numOfDeadlines * 100);
        percentageOfDoneEvents = (int) (numOfDoneEvents / numOfEvents * 100);
        percentageOfDoneTasks = (int) (numOfDoneTasks / tasks.getSize() * 100);
    }

    public double getNumOfTodos() {
        return numOfTodos;
    }

    public double getNumOfDoneTodos() {
        return numOfDoneTodos;
    }

    public double getNumOfDeadlines() {
        return numOfDeadlines;
    }

    public double getNumOfDoneDeadlines() {
        return numOfDoneDeadlines;
    }

    public double getNumOfEvents() {
        return numOfEvents;
    }

    public double getNumOfDoneEvents() {
        return numOfDoneEvents;
    }

    public double getNumOfDoneTasks() {
        return numOfDoneTasks;
    }

    public int getPercentageOfDoneTodos() {
        return percentageOfDoneTodos;
    }

    public int getPercentageOfDoneTasks() {
        return percentageOfDoneTasks;
    }

    public int getPercentageOfDoneDeadlines() {
        return percentageOfDoneDeadlines;
    }

    public int getPercentageOfDoneEvents() {
        return percentageOfDoneEvents;
    }
}
