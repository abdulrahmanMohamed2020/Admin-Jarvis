package org.labs247.constants;

import java.text.SimpleDateFormat;
import java.util.*;

public class EnrolmentTestData {



    public static Map<String,String> getEnrolmentData() {
        Map<String,String> dataMap = new LinkedHashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHms");
        String firstName = "Auto_FN"+formatter.format(new Date());
        String lastName = "Auto_LN"+formatter.format(new Date());

        dataMap.put("type","Student");
        dataMap.put("firstName",firstName);
        dataMap.put("lastName",lastName);

        dataMap.put("dateOfBirth","14 Feb 1995");
        dataMap.put("gender","Male");
        dataMap.put("email","auto_"+formatter.format(new Date())+"@abc.xyz");

        dataMap.put("countryOfResidence","Afghanistan");
        dataMap.put("countryOfDestination","Albania");

        dataMap.put("tripStartDate","10 Jan 2024");
        dataMap.put("tripEndDate","11 Jan 2024");


        dataMap.put("product","Multi-Risk Ireland");
        dataMap.put("campus","Westminster School");

        return dataMap;
    }


}
