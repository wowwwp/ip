package ella.task;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Compares two {@link Task} objects based on the dates returned by the tasks.
 *
 * <p>This comparator compares tasks by their associated date, ordering them in ascending
 * order (earliest deadline first). If two tasks have the same deadline, their order
 * relative to each other remains unchanged.</p>
 */
public class TaskComparator implements Comparator<Task> {
    /**
     * {@inheritDoc}
     *
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return a negative integer if {@code t1} is less than {@code t2},
     *         zero if they are equal, or a positive integer if {@code t1} is greater than {@code t2}.
     */
    @Override
    public int compare(Task t1, Task t2) {
        LocalDateTime d1 = t1.compareDate();
        LocalDateTime d2 = t2.compareDate();
        return d1.compareTo(d2);

    }

}
