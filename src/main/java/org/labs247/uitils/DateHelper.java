package org.labs247.uitils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    public static String getCurrentDatePlus(int daysToAdd) {

        LocalDate currentDatePlusOffset = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

        return currentDatePlusOffset.format(formatter);
    }
}
