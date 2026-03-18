package app.taskmanager;

import app.taskmanager.model.Task;
import app.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldSaveAndFindTask() {
        Task task = new Task("Zadanie testowe", false);
        Task saved = taskRepository.save(task);

        assertThat(taskRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldDeleteTask() {
        Task task = taskRepository.save(new Task("Do usunięcia", false));
        taskRepository.deleteById(task.getId());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }

    @Test
    void shouldReturnAllTasks() {
        taskRepository.save(new Task("Zadanie 1", false));
        taskRepository.save(new Task("Zadanie 2", false));
        List<Task> tasks = taskRepository.findAll();

        assertThat(tasks).hasSizeGreaterThanOrEqualTo(2);
    }
}