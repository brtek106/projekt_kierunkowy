package pl.bartek.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam("title") String title) {
        repository.save(new Task(title, false));
        return "redirect:/";
    }

    @PostMapping("delete")
    public String deleteTask(@RequestParam("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brak zadania o id: " + id));
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute("task") Task task) {
        repository.save(task);
        return "redirect:/";
    }
}
