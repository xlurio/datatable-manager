package com.calegario.dtmanager;

import java.time.format.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.AssertionError;
import java.time.temporal.ChronoField;
import com.calegario.dtmanager.DTManager;


public class DTManagerTests {

    @Test
    public void testGetLastObjects() {
        /**
         * Test getting the most recent item from a table (List<String[])
        **/
        // Test parameters
        List<String[]> table = new ArrayList<String[]>(sampleDT());
        DateTimeFormatter formatter =
            new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .toFormatter();
        // Expected results
        List<String[]> expectedTable =
            new ArrayList<String[]>(sampleDTLastOnly());
        List<String[]> unexpectedTable =
            new ArrayList<String[]>(sampleDTEarliest());
        // Result
        List<String[]> result = new ArrayList<String[]>(
            DTManager.getLastObjects(table, 0, 1, formatter)
        );
        // Assertions
        getTableAssertions(expectedTable, unexpectedTable, result);
    }

    @Test
    public void testFilterTable() {
        /**
         * Test filtering a table by a value inside a column
        **/
        // Test parameters
        List<String[]> table = new ArrayList<String[]>(sampleDT());
        // Expected results
        List<String[]> expectedTable = new ArrayList<String[]>(sampleDTJohn());
        List<String[]> unexpectedTable = new ArrayList<String[]>(sampleDTBen());
        // Result
        List<String[]> result = new ArrayList<String[]>(
            DTManager.filterTable(table, "John", 0)
        );
        // Assertions
        getTableAssertions(expectedTable, unexpectedTable, result);
    }

    @Test
    public void testInverseFilterTable() {
        /**
         * Test filtering a table by a value inside a column
        **/
        // Test parameters
        List<String[]> table = new ArrayList<String[]>(sampleDT());
        // Expected results
        List<String[]> expectedTable = new ArrayList<String[]>(sampleDTBen());
        List<String[]> unexpectedTable = new ArrayList<String[]>(sampleDTJohn());
        // Result
        List<String[]> result = new ArrayList<String[]>(
            DTManager.inverseFilterTable(table, "John", 0)
        );
        // Assertions
        getTableAssertions(expectedTable, unexpectedTable, result);
    }

    private void getTableAssertions(List<String[]> expectedTable,
                                    List<String[]> unexpectedTable,
                                    List<String[]> result)
    {
        assertEquals(expectedTable.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertTrue(Arrays.equals(
                result.get(i),
                expectedTable.get(i)
            ));
            assertFalse(Arrays.equals(
                result.get(i),
                unexpectedTable.get(i)
            ));
        }
    }

    private List<String[]> sampleDT() {
        /**
         * Returns a sample datatable
        **/
        List<String[]> list = new ArrayList<String[]>(sampleDTEarliest());
        list.addAll(sampleDTLastOnly());
        return list;
    }

    private List<String[]> sampleDTJohn() {
        /**
         * Returns a sample datatable where "John" appears
        **/
        List<String[]> list = new ArrayList<String[]>();
        String[] row1 = {
            "John",
            "2015-05-05 10:15:30"
        };
        String[] row2 = {
            "John",
            "2021-05-05 10:15:30"
        };
        list.add(row1);
        list.add(row2);
        return list;
    }

    private List<String[]> sampleDTBen() {
        /**
         * Returns a sample datatable where "John" appears
        **/
        List<String[]> list = new ArrayList<String[]>();
        String[] row1 = {
            "Ben",
            "2021-05-05 10:15:30"
        };
        String[] row2 = {
            "Ben",
            "2022-05-05 10:15:30"
        };
        list.add(row1);
        list.add(row2);
        return list;
    }

    private List<String[]> sampleDTEarliest() {
        /**
         * Returns a sample datatable with the earliest dates only
        **/
        List<String[]> list = new ArrayList<String[]>();
        String[] row1 = {
            "John",
            "2015-05-05 10:15:30"
        };
        String[] row2 = {
            "Ben",
            "2021-05-05 10:15:30"
        };
        list.add(row1);
        list.add(row2);
        return list;
    }

    private List<String[]> sampleDTLastOnly() {
        /**
         * Returns a sample datatable with the most current dates only
        **/
        List<String[]> list = new ArrayList<String[]>();
        String[] row1 = {
            "John",
            "2021-05-05 10:15:30"
        };
        String[] row2 = {
            "Ben",
            "2022-05-05 10:15:30"
        };
        list.add(row1);
        list.add(row2);
        return list;
    }

}
