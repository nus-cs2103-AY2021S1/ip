package duke.task;

public class Task {
  String taskName;
  boolean isCompleted;

  /**
   * Create a new duke.task.Task.
   *
   * @param task Name of the duke.task.Task
   */
  public Task(String task) {
    this.taskName = task;
    this.isCompleted = false;
  }

  /**
   * Create a new task whose status is isCompleted.
   * @param task duke.task.Task name
   * @param isCompleted Whether it is completed
   */
  public Task(String task, boolean isCompleted) {
    this.taskName = task;
    this.isCompleted = isCompleted;
  }

  private boolean matchesWord(String keyword) {
    String[] getWords = taskName.split(" ");
    int numberMatches = 0;
    for (String word : getWords) {
      if (word.equals(keyword)) {
        numberMatches++;
      }
    }
    return numberMatches > 0;
  }

  /**
   * Given an array of words, check if the task name matches any of them.
   *
   * @param words Array of words
   * @return Whether task name matches
   */
  public boolean matchesWordList(String[] words) {
    boolean matchesTask = false;
    for (String word : words) {
      if (matchesWord(word)) {
        matchesTask = true;
      }
    }
    return matchesTask;
  }

  /**
   * Sets a task as completed.
   *
   * @return A new duke.task.Task whose status is completed
   */
  public Task setTaskAsCompleted() {
    return new Task(taskName, true);
  }

  @Override
  public String toString() {
    String tickOrCross = isCompleted ? "DONE" : "NOT DONE";
    return String.format("%s%s%s %s", "[", tickOrCross, "] ", taskName);
  }
}
