package com.frontend.todoList.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String iconClass;
    private Integer count;
    @JsonProperty(value = "isLastDefaultCategory")
    private boolean isLastDefaultCategory;
    @JsonProperty(value = "isDefaultCategory")
    private boolean isDefaultCategory;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iconClass='" + iconClass + '\'' +
                ", count=" + count +
                ", isLastDefaultCategory=" + isLastDefaultCategory +
                ", isDefaultCategory=" + isDefaultCategory +
                '}';
    }
}
