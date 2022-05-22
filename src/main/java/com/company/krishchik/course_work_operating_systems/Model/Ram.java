package com.company.krishchik.course_work_operating_systems.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ram {

    private String capacity;
    private String maxVoltage;
    private String speed;
    private String manufacturer;

}
