package pl.coderslab.charity.institution.DTOS;

import lombok.Data;


@Data
public class InstitutionDTO {

    private Long id;

    private String name;

    private String description;

    private boolean status;


}
