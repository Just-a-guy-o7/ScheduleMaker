package com.ScheduleMaker.ScheduleMaker.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {
    public String topicName;
    public Integer orderIndex;
    public Double minHours;
    public Integer basePriority;
}
