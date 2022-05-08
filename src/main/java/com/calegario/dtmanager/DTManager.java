package com.calegario.dtmanager;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.*;

public class DTManager{

    public static List<String[]> getLastObjects(List<String[]> table,
                                                int toFilterIndex,
                                                int dateIndex,
                                                DateTimeFormatter dtFormatter)
    {
        /**
         * Returns a list with only unique items filtered by the instances with
         the last date
        **/
        List<String[]> list = new ArrayList<String[]>();
        List<String[]> currData = new ArrayList<String[]>();
        LocalDateTime date;
        String dateStr;
        List<LocalDateTime> dates = new ArrayList<LocalDateTime>();
        int lastDateIndex;
        for (int i = 0; i < table.size(); i++) {
            currData = filterTable(table,
                                   table.get(i)[toFilterIndex],
                                   toFilterIndex);
            dates = new ArrayList<LocalDateTime>();
            for (int j = 0; j < currData.size(); j++) {
                date = LocalDateTime.parse(currData.get(j)[dateIndex],
                                           dtFormatter);
                dates.add(date);
            }
            lastDateIndex = dates.indexOf(Collections.max(dates));
            list.add(currData.get(lastDateIndex));
            table = inverseFilterTable(table,
                                       table.get(i)[toFilterIndex],
                                       toFilterIndex);
        }
        return list;
    }

    public static List<String[]> filterTable(List<String[]> table,
                                             String valueToLook,
                                             int toFilterIndex)
    {
        /**
         * Returns a list with only items where the passed value appears in the
         specified column
        **/
        List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i)[toFilterIndex].contains(valueToLook)) {
                list.add(table.get(i));
            }
        }
        return list;
    }

    public static List<String[]> inverseFilterTable(List<String[]> table,
                                                    String valueNotToLook,
                                                    int toFilterIndex)
    {
        /**
         * Returns a list with only items where the passed value does not
         appears in the specified column
        **/
        List<String[]> list = new ArrayList<String[]>();
        for (int i = 0; i < table.size(); i++) {
            if (!table.get(i)[toFilterIndex].contains(valueNotToLook)) {
                list.add(table.get(i));
            }
        }
        return list;
    }
}
