/*
 * Name: Zubair Khalid
 * Matriculation Number: S1843905
 */

package com.khalidzubair.khalid_zubair_s1834905;


public class FormatData {


    public String longDateToShort(String stringDate) {
        String regex = "\\d+";
        StringBuilder result = new StringBuilder();
        String[] words = stringDate.split(" ");
        for (String word : words) {
            if (word.matches(regex)) {
                result.append(word).append("/");
            } else if (!getNumberOfMonth(word).isEmpty()) {
                result.append(getNumberOfMonth(word)).append("/");
            }
        }
        return result.substring(0, result.length() - 1); // take the last / off the end
    }

    public String[] getDates(String date) throws StringIndexOutOfBoundsException {
        if (!date.contains("Start Date: ") || !date.contains("End Date: ")) {
            return null;
        } else {
            String indexStartDate = date.substring(date.indexOf("Start Date: "), date.indexOf(':'));
            String data1 = date.substring(indexStartDate.length() + 2, date.indexOf('<'));
            String leftOver = date.substring(date.indexOf('>'));

            String endDateIndex = leftOver.substring(leftOver.indexOf("End Date: "), date.indexOf(':'));
            String data2 = "";
            if (date.contains("<br />Delay")) {
                data2 = leftOver.substring(endDateIndex.length() + 2, leftOver.indexOf('<'));
            } else {
                data2 = leftOver.substring(endDateIndex.length() + 2);
            }
            String[] results = new String[2];
            results[0] = data1;
            results[1] = data2;
            return results;
        }
    }

    public String getDescription(String description) {
        int result = description.lastIndexOf("<br />");

        if (result == -1) {
            return description;
        } else {
            return description.substring(result + 6, description.length());
        }
    }

    public String getNumberOfMonth(String month) {
        switch (month) {
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            default:
                return "";
        }
    }

}
