package com.example.demo.objectsForTopic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting implements Serializable  {
    private String msg;
    private String name;
}
