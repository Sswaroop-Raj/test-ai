package com.spring.ai.demo.first_project.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {

    private String title;
    private String description;
    private Date createdDate;
}
