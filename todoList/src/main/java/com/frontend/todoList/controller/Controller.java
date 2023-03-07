package com.frontend.todoList.controller;

import com.frontend.todoList.model.Category;
import com.frontend.todoList.model.Task;
import com.frontend.todoList.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("todo")
public class Controller {

    List<Task> tasks = new ArrayList<>();
    List<User> users = new ArrayList<>();
    static List<Category> categories = new ArrayList<>();

    boolean check = false;

    int categoryId = 5;
    int taskId = 0;
    int userId = 0;

    public static void generateDefaultCategory() {
        Category myDay = new Category(1, "My Day", "fa-solid fa-sun", 0, false, true);
        Category important = new Category(2, "Important", "fa-regular fa-star", 0, false, true);
        Category planned = new Category(3, "Planned", "fa-regular fa-calendar", 0, false, true);
        Category assignedToMe = new Category(4, "Assigned to me", "fa-solid fa-user", 0, false, true);
        Category tasks = new Category(5, "Tasks", "fa-solid fa-house", 0, true, true);
        categories.add(myDay);
        categories.add(important);
        categories.add(planned);
        categories.add(assignedToMe);
        categories.add(tasks);
    }


    @PostMapping("/task")
    public int saveAndUpdateTask(@RequestBody Task task) {
        if(task.getId() > 0) {
            Task oldTask = tasks.get(task.getId()-1);
            oldTask.setCategoryIds(task.getCategoryIds());
            oldTask.setNote(task.getNote());
            oldTask.setName(task.getName());
            oldTask.setImportant(task.isImportant());
            oldTask.setCompleted(task.isCompleted());
            System.out.println("oldTask - > : " + oldTask);
        } else {
            task.setId(++taskId);
            tasks.add(task);
            System.out.println("save new task : " + task);
        }
        return task.getId();
    }


    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping("/category")
    public Category saveAndUpdateCategory( @RequestBody Category category ) {
        category.setId(++categoryId);
        categories.add(category);
        System.out.println("category : " + category);
        return category;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        if (!check) {
            generateDefaultCategory();
            check = true;
        }
        return categories;
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id) {
        Task task = new Task();
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getId() == id) {
                task = tasks.get(i);
                break;
            }
        }
        System.out.println(task);
        return task;
    }

    @GetMapping("/taskByCategory/{selectedCategoryId}")
    public List<Task> getTaskByCategoryId(@PathVariable int selectedCategoryId) {
        List<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            for (int taskCategoryId : task.getCategoryIds()) {
                if (taskCategoryId == selectedCategoryId) {
                    taskList.add(task);
                }
            }
        }
        return taskList;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        user.setId(++userId);
        users.add(user);
        System.out.println(user);
        return user;
    }

    @PostMapping("/login")
    public User getUser(@RequestBody User clientUser) {
        System.out.println("clientUser : " + clientUser);
        User user = new User();
        for (User availableUser : users) {
            System.out.println("availableUser : " + availableUser);
            System.out.println("get user");
            if (Objects.equals(availableUser.getName(), clientUser.getName())) {
                System.out.println("user is present : " + availableUser.getId());
                user = availableUser;
            }
        }
        return user;
    }
}
