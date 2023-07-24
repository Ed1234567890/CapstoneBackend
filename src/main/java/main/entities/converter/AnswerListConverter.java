/**
 * Convertor for changing from list of string of answer option to Database string format and vice versa
 */
package main.entities.converter;

import jakarta.persistence.AttributeConverter;
import java.util.*;

public class AnswerListConverter implements AttributeConverter<List<String>, String> {
        //converts list to database string
        @Override
        public String convertToDatabaseColumn(List<String> attribute) {
            return attribute == null ? null : String.join(",",attribute);
        }
        //converts string from database to list
        @Override
        public List<String> convertToEntityAttribute(String dbData) {
            return dbData == null ? Collections.emptyList() : Arrays.asList(dbData.split(","));
        }
    }

