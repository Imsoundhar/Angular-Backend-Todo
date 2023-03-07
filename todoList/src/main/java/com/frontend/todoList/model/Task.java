package com.frontend.todoList.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Task {
    private int id;
    private String name;
    private List<Integer> categoryIds;
    private String note;
    @JsonProperty(value="isImportant")
    private boolean isImportant;
    @JsonProperty(value="isCompleted")
    private boolean isCompleted;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryIds=" + categoryIds +
                ", note='" + note + '\'' +
                ", isImportant=" + isImportant +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
