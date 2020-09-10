package duke.tasks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import duke.exceptions.NoSuchTentativeDateException;
import duke.utils.DukeDateTime;

/**
 * Represents the tentative schedule of an event.
 */
public class TentativeSchedule {

    private List<DukeDateTime> dukeDateTimes;

    /**
     * Constructor.
     *
     * @param dukeDateTimes The possible DukeDateTime objects specified.
     */
    public TentativeSchedule(DukeDateTime... dukeDateTimes) {
        this.dukeDateTimes = Arrays.asList(dukeDateTimes);
    }

    /**
     * Gets the list of DukeDateTime objects.
     *
     * @return The list of DukeDateTime objects that the user specified.
     */
    public List<DukeDateTime> getDukeDateTimes() {
        dukeDateTimes.sort(Comparator.naturalOrder());
        return dukeDateTimes;
    }

    @Override
    public String toString() {
        return getDukeDateTimes().toString();
    }

    public DukeDateTime getDateAtIndex(int index) throws NoSuchTentativeDateException {
        try {
            return dukeDateTimes.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTentativeDateException();
        }
    }

}
