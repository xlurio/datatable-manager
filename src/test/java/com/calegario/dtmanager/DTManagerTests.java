package com.calegario.dbmanager;

import junit.framework.*;
import java.util.*;
import java.lang.AssertionError;
import com.calegario.dbmanager.DBManager;


class DTManagerTest extends TestCase {

    public void testGetLastObjects() {
        /**
         * Test getting the most recent item from a table (List<String[])
        **/
        // Test parameters
        List<String[]> table = new ArrayList<String[]>(sampleDT());
        // Expected results
        List<String[]> expectedTable =
            new ArrayList<String[]>(sampleDTLastOnly());
        List<String[]> unexpectedTable =
            new ArrayList<String[]>(sampleDTEarliest());
        // Result
        List<String[]> result = new ArrayList<String[]>(
            DBManager.getLastObjects(table, 0, 1)
        );
        // Assertions
        getTableAssertions(expectedTable, unexpectedTable, result);
    }

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
            DBManager.filterTable(table, "John", 0)
        );
        // Assertions
        getTableAssertions(expectedTable, unexpectedTable, result);
    }

    private void getTableAssertions(List<String[]> expectedTable,
                                    List<String[]> unexpectedTable,
                                    List<String[]> result)
    {
        try{
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < expectedTable.size(); i++){
                    assertTrue(Arrays.equals(
                        result.get(i),
                        expectedTable.get(j)
                    ));
                    assertFalse(Arrays.equals(
                        result.get(i),
                        unexpectedTable.get(j)
                    ));
                }
            }

        } catch (AssertionError e) {
            e.printStackTrace();
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
