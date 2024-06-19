package com.skillswapcomunity.skillswapcomunity.dto;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Long id;
    private int status;
    private Person sender;
    private Person recipient;
}
