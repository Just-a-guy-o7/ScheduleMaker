package com.ScheduleMaker.ScheduleMaker.DataTransferObjects;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {
    private String name;
    private ArrayList<TopicDTO> topics;
    private Integer selfRatedFamiliarity;
    private Double totalHoursUsedInMin;
    private Integer totalPriority;
}
