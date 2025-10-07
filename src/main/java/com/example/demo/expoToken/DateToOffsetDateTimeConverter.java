package com.example.demo.expoToken;

import org.springframework.core.convert.converter.Converter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(Date source) {
        return source.toInstant().atOffset(ZoneOffset.UTC);
    }
}
