package pl.bartek.taskmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addTask(@RequestParam String title) {
        repository.save(new Task(title, false));
        return "redirect:/";
    }

    @PostMapping("delete")
    public String deleteTask(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
