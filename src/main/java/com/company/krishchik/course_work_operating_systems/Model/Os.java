package com.company.krishchik.course_work_operating_systems.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Os {

    private String name;
    private String manufacturer;
    private String systemName;
    private String bootDevice;
    private String countryCode;
    private String installDate;

}
