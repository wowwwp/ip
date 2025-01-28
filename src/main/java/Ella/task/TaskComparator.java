package Ella.task;
import java.time.LocalDateTime;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        LocalDateTime d1 = t1.compareDate();
        LocalDateTime d2 = t2.compareDate();

        return d1.compareTo(d2);

    }

}
