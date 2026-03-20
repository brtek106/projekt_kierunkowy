package app.taskmanager;

import app.taskmanager.controller.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class TaskControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void shouldReturnIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void shouldRedirectAfterAddingTask() throws Exception {
        mockMvc.perform(post("/add")
                        .param("title", "Poprawne zadanie")
                        .param("description", "Opis"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void shouldRejectInvalidTask() throws Exception {
        mockMvc.perform(post("/add")
                        .param("title", "")
                        .param("description", "Opis"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}