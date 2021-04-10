package com.example.examproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Модель данных учителя")
public class TeacherDto {

    @Schema(title = "Идентификатор учителя")
    private int teacherId;
    @Schema(title = "Имя учителя")
    private String name;
    @Schema(title = "Кафедра")
    private String department;
}
