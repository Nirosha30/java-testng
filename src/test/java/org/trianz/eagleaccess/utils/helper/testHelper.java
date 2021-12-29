package org.trianz.eagleaccess.utils.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testHelper {

    public static String compareListOfMaps(List<Map> result1, List<Map> result2) {
        return compareListOfMaps(result1, result2, "none");
    }
    public static String compareListOfMaps(List<Map> result1, List<Map> result2, String sortMapsByField) {
        // DIFFERING NUMBER OF MAPS IN LIST? - get out
        if (result1.size() != result2.size()) {
            return "not equal: different number of maps";
        }

        // BOTH ZERO SIZE - sounds good
        if (result1.size() == 0 && result2.size() == 0) {
            return "equal";
        }

        // IS IT AN ERROR WE'RE COMPARING?
        if (result1.get(0).containsKey("NOT_FOUND")) {
            // if the values are not the same return "not equal..." otherwixe return "equal"
            if (!result1.get(0).get("NOT_FOUND").toString().equals(result2.get(0).get("NOT_FOUND").toString())) {
                return "not equal: rest reporting 'NOT_FOUND', but soap is not";
            } else {
                return "equal";
            }
        }

        // SORT THE LIST so the maps are in the same order
        if(!sortMapsByField.equals("none")) {
            List<Map> result2_sorted = new ArrayList<Map>();
            for (Map map : result1) {
                String fieldValue1 = map.get(sortMapsByField).toString();
                for (Map map2 : result2) {
                    String fieldValue2 = map2.get(sortMapsByField).toString();
                    System.out.println("=========== " + fieldValue1 + "  " + fieldValue2);
                    if (fieldValue1.equals(fieldValue2)) {
                        result2_sorted.add(map2);
                    }
                }
            }
        }

        // COMPARE THE MAPS
        for (int x = 0; x < result1.size(); x++) {
            String compare = compareMaps(result1.get(x), result2.get(x));
            if (!compare.equals("equal")) {
                return compare;
            }
        }
        return "equal";
    }



    // COMPARE TWO MAPS
    // returns string with "equal" if maps are equal, or "not equal" with
    // summary of items that are not equal
    public static String compareMaps(Map map1, Map map2) {
        String result = "";
        for (Object key : map1.keySet()) {
            if (!valueToStringOrEmpty(map1, key.toString()).equals(valueToStringOrEmpty(map2, key.toString()))){
                result += "KEY: " + key + "  [Actual , Expected],[ '" + valueToStringOrEmpty(map1, key.toString()) + "', '" + valueToStringOrEmpty(map2, key.toString()) + "'] ### \n";
            }
        }

        if(result.isEmpty()) { result = "equal";  }
        System.out.println("Result Value is "+result);
        return result;

    }

    // COMPARE TWO LISTS OF MAPS (List<Map>) based on "key"
    // Here the extra parameter "key" is added. This key is used to find the correct object form the list of maps for comparision.
    public static String compareListOfMapsUsingKey(List<Map> result1, List<HashMap> result2, String key) {
        // DIFFERING NUMBER OF MAPS IN LIST? - get out

        if (result1.size() != result2.size()) {
            System.out.println(result1.size()+" - "+result2.size());
            return "not equal: different number of maps";
        }

        // BOTH ZERO SIZE - sounds good
        if (result1.size() == 0 && result2.size() == 0) {
            return "equal";
        }

        // IS IT AN ERROR WE'RE COMPARING?
        if (result1.get(0).containsKey("NOT_FOUND")) {
            // if the values are not the same return "not equal..." otherwixe return "equal"
            if (!result1.get(0).get("NOT_FOUND").toString().equals(result2.get(0).get("NOT_FOUND").toString())) {
                return "not equal: rest reporting 'NOT_FOUND', but soap is not";
            } else {
                return "equal";
            }
        }

        // COMPARE THE MAPS
        String flag ="";
        String compare ="";
        for(int j=0;j<result1.size();j++) {
            for (int x = 0; x < result2.size(); x++) {
                if (result1.get(j).get(key).toString().equals(result2.get(x).get(key).toString())) {
                    compare = compareMaps(result1.get(j), result2.get(x));
                    if (!compare.equals("equal")) {
                        flag= flag+ " \n For Key Value: "+result1.get(j).get(key) +"\n"+
                                "\n Expecting : "+result1.get(j).toString()+
                                "\n Found: "+result2.get(x).toString();
                    }

                    if (flag.isEmpty()) {
                        flag="equal";
                    }
                }
            }
        }
        return flag;
    }

    private static String valueToStringOrEmpty(Map<String, ?> map, String key) {
        Object value = map.get(key);
        return value == null ? "" : value.toString();
    }



}
