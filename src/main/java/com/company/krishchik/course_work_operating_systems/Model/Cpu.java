package com.company.krishchik.course_work_operating_systems.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cpu {

    private String name;
    private String manufacturer;
    private String numberOfCores;
    private String L2CacheSize;
    private String L3CacheSize;
    private String ThreadCount;


}
