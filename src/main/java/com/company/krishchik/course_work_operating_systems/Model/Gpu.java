package com.company.krishchik.course_work_operating_systems.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Gpu {

    private String name;
    private String manufacturer;
    private String chipType;
    private String dacType;
    private String deviceType;
}
