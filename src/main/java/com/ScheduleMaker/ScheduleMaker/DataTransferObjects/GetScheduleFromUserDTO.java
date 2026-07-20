package com.ScheduleMaker.ScheduleMaker.DataTransferObjects;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetScheduleFromUserDTO {
    private String name;
    private ArrayList<SubjectDTO> subjects;
    private String startDate;
    private String endDate;
    private Double dailyAvailableHours;
    private Integer totalFamiliarityForSubjects;
    private Double totalHoursUsedInMin;
}
