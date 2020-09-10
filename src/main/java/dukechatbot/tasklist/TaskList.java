package dukechatbot.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dukechatbot.constant.DukeConstants;
import dukechatbot.enums.StringMatchEnum;
import dukechatbot.task.Task;

/**
 * Represents a list of task where you can add, delete tasks and list the tasks.
 */
public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds task to task list.
     * 
     * @param task
     */
    public void add(Task task) {
        assert(!Objects.isNull(task));
        this.list.add(task);
    }

    /**
     * Returns task info for each task in task list.
     * 
     * @return List of task info.
     */
    public List<String> getListInfo() {
        List<String> listInfo = new ArrayList<>();
        listInfo.add(DukeConstants.LIST_OUTPUT);

        int num = 0;
        for (Task output : this.list) {
            num++;
            listInfo.add(String.format("%d.%s", num, output));
        }
        return listInfo;
    }

    /**
     * Marks a task done in the task list given its index.
     * 
     * @param idx
     * @return Task String information.
     */
    public String markDone(int idx) {
        Task task = this.list.get(idx);
        task.markDone();
        return task.toString();
    }

    /**
     * Returns the task list.
     * @return task list.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Returns the current size of the task list.
     * @return Current size of task list.
     */
    public int getCurrentSize() {
        return this.list.size();
    }

    /**
     * Deletes a task in the task list given its index.
     * 
     * @param idx
     * @return Deleted task string information.
     */
    public String delete(int idx) {
        Task task = this.list.remove(idx);
        return task.toString();
    }

    /**
     * Returns a list of tasks in the task list that matches the keyword.
     * tasks whose title matches fully with keyword comes first,
     * followed by tasks whose space-delimited title contain the keyword,
     * followed by tasks whose title contains the substring keyword.
     * 
     * @param searchKeyword
     * @return List of tasks in the task list that matches the search keyword.
     */
    public List<Task> findMatches(String searchKeyword) {
        List<Task> result = new ArrayList<>();
        List<Task> fullMatchList = new ArrayList<>();
        List<Task> wordMatchList = new ArrayList<>();
        List<Task> partialMatchList = new ArrayList<>();
        
        for (Task task : this.list) {
            StringMatchEnum nameMatchEnum = task.contains(searchKeyword);
            switch (nameMatchEnum) {
            case FULL_MATCH:
                fullMatchList.add(task);
                break;
            case WORD_MATCH:
                wordMatchList.add(task);
                break;
            case PARTIAL_MATCH:
                partialMatchList.add(task);
                break;
            default:
            }
        }
        
        result.addAll(fullMatchList);
        result.addAll(wordMatchList);
        result.addAll(partialMatchList);
        
        return result;
    } 
}
