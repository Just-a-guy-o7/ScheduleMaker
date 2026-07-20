package com.ScheduleMaker.ScheduleMaker.DataTransferObjects;

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
public class SubjectNameAndSyllabusDTO {
    private String subjectName;
    private String subjectSyllabus;
    
}
