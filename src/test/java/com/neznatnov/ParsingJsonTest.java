package com.neznatnov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;


public class ParsingJsonTest {
    private ClassLoader cl = ParsingJsonTest.class.getClassLoader();

    @DisplayName("Чтение JSON файла")
    @Test
    void jsonParseTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        try (
                InputStream resource = cl.getResourceAsStream("fruits.json");
                InputStreamReader reader = new InputStreamReader(resource);
        ) {

            Fruits dataFruit = objectMapper.readValue(reader, Fruits.class);

            Assertions.assertEquals("banana", dataFruit.getFruits().get(1).getName());
            Assertions.assertEquals("green", dataFruit.getFruits().get(1).getColors().get(1));

        }


        }
    }
