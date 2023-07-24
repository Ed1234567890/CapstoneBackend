/**
 * Convertor for changing from list of string of Question ID to Database string format and vice versa
 */
package main.entities.converter;

import jakarta.persistence.AttributeConverter;
import java.util.*;

public class QuestionListConverter implements AttributeConverter<List<String>, String> {
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

//public class QuestionListConverter implements AttributeConverter<List<Question>, String> {
//    @Override
//    public String convertToDatabaseColumn(List<Question> attribute) {
//        System.out.println("convertToDatabaseColumn");
//        System.out.println(attribute.get(0));
//        String sq = new String();
////        if (!attribute.isEmpty()) {
////            for (Question qn : attribute) {
////                sq.join(",", qn.toString());
////            }
////        }
//        return attribute == null ? null : attribute.get(0).toString();
//    }
//
//    @Override
//    public List<Question> convertToEntityAttribute(String dbData) {
//
//        List<Question> qns = new ArrayList<>();
//        System.out.println("convertToEntityAttribute");
////        System.out.println(dbData.);
//
//        String[] str= dbData.split(",");
//        for (String s: str) {
//            System.out.print(s);
//        }
//        System.out.println();
//
//        int[] arr = new int[str.length];
//
//        for (int i = 0; i < str.length; i++) {
//            arr[i] = Integer.valueOf(str[i]);
//            qns.add(new Question(1, "qnStr","category", true," String option1", "String option2", "String option3", "String option4", (byte) 1)) ;
//            }
//
//        return dbData == null ? Collections.emptyList() : qns;
//    }
//}