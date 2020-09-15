package task;

import java.util.Comparator;

/**
 * Comparator used to compare tasks.
 *
 * <p>The 'TaskComparator' supports operators, supported include: </p>
 *
 * <p> (i) comparing 2 tasks by their type followed by alphabetical order of description </p>
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Compares two Tasks.
     *
     * @param a the Task to be compared.
     * @param b the Task to be compared to.
     * @return comparison result. Returns negative integer, zer0, positive integer
     * if (a < b), (a == b), (a > b) respectively.
     */
    @Override
    public int compare(Task a, Task b) {
        char typeA = a.getType().charAt(0);
        char typeB = b.getType().charAt(0);

        if (typeA == typeB) {
            String descriptionA = a.getDescription();
            String descriptionB = b.getDescription();

            int sizeA = descriptionA.length();
            int sizeB = descriptionB.length();
            int lowestIndex = Math.min(sizeA, sizeB);

            for (int i = 0; i < lowestIndex; i++) {
                if (descriptionA.charAt(i) != descriptionB.charAt(i)) {
                    return descriptionA.charAt(i) - descriptionB.charAt(i);
                }
            }

            return sizeA - sizeB;
        } else {
            return typeA - typeB;
        }
    }
}
