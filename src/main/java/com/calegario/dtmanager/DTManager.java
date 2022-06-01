package com.calegario.dtmanager;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.*;

public class DTManager{

    public static List<String[]> getLastObjects(List<String[]> table,
                                                int dateIndex,
                                                DateTimeFormatter dtFormatter)
    {
        /**
         * Returns a list with only unique items filtered by the instances with
         the last date
        **/
        String[] currentRow;
        String currentDateAsText;
        LocalDateTime currentDate;
        List<LocalDateTime> dates = new ArrayList<LocalDateTime>();
        for (int row = 0; row < table.size(); row++) {
            currentRow = table.get(row);
            currentDateAsText = currentRow[dateIndex];
            currentDate = LocalDateTime.parse(currentDateAsText,
                                              dtFormatter);
            dates.add(currentDate);
        }
        LocalDateTime mostRecentDate = Collections.max(dates);
        int mostRecentDateIndex = dates.indexOf(mostRecentDate);
        String[] mostRecentRow = table.get(mostRecentDateIndex);
        List<String[]> filteredTable = new ArrayList<String[]>();
        filteredTable.add(mostRecentRow);
        return filteredTable;
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
