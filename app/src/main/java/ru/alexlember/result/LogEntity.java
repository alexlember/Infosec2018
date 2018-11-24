package ru.alexlember.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogEntity implements Serializable {
    private String text;
}
