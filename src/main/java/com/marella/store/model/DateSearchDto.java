package com.marella.store.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class DateSearchDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
