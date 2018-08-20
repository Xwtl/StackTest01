package com.ernok.boottest003.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

// Converts LocalDate objects into SQL date format and vice versa.
// Necessary to persist dates into the database in proper format.

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate,
                                                                       Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
