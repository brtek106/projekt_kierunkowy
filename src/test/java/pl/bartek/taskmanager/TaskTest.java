package pl.bartek.taskmanager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void shouldCreateTaskWithTitleAndStatus() {
        Task task = new Task("Testowe zadanie", false);

        assertEquals("Testowe zadanie", task.getTitle());
        assertFalse(task.isCompleted());
    }

    @Test
    void shouldUpdateTaskId() {
        Task task = new Task("Zadanie", false);

        task.setId(100L);
        assertEquals(100L, task.getId());
    }
}
