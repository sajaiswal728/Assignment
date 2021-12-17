package com.example.Assignment.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private long id;
    private String categoryCode;
    private String value;
    private Date updatedDate;
    private Timestamp updatedTs;
}
