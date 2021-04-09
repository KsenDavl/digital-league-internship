package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Модель данных студента")
public class StudentDto {

    @Schema(title = "Идентификатор студента")
    private int studentId;
    @Schema(title = "Имя студента")
    private String name;
    @Schema(title = "Специальность")
    private String specialty;
    @Schema(title = "Курс")
    private int year;

}
