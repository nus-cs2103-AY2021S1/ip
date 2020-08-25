package main.java;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    ArrayList<Task> tList;

    public TaskList () {
        tList = new ArrayList<Task>();
    }

    public TaskList (ArrayList<Task> t) {
        tList = t;
    }

}
