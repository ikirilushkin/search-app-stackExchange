package ru.kirilushkin.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Date;

public class DateFromSecondsDeserializer extends StdDeserializer<Date> {

    public DateFromSecondsDeserializer() {
        this(null);
    }

    protected DateFromSecondsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String seconds = jsonParser.getText();
        try {
            Long ms = Long.parseLong(seconds) * 1000L;
            return new Date(ms);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }
}
