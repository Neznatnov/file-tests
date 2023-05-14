package com.neznatnov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruits {
    private List<Fruit> fruits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Fruit {
        private String name;
        private List<String> colors;
    }
}
