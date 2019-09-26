package v2.service;

import v2.entity.Record;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fomichev Yuri on 26.09.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Service {
    private final static String SPLIT = "\"(.*?)\"";

    public static Record createRecord(String line) {
        Record record = new Record();
        Pattern pattern = Pattern.compile(SPLIT);
        Matcher matcher = pattern.matcher(line);
        int group = 1;

        while (matcher.find()) {
            String string = matcher.group();
            String value = string.substring(1, string.length() - 1);

            if (group == 1) {
                record.setTimestamp(value);
            } else if (group == 2) {
                record.setReferer(value);
            } else if (group == 3) {
                record.setMethod(value);
            } else if (group == 4) {
                record.setURL(value);
            } else if (group == 5) {
                record.setQuery(value);
            } else if (group == 6) {
                record.setStatus(Integer.parseInt(value));
            } else if (group == 7) {
                record.setSize(Long.parseLong(value));
            } else if (group == 8) {
                record.setTime(Long.parseLong(value));
            } else if (group == 9) {
                record.setUserName(value);
            } else if (group == 10) {
                if (!value.equals("-")) {
                    record.setUserID(Integer.parseInt(value));
                }
            } else if (group == 11) {
                record.setSessionID(value);
            }

            group += 1;
        }

        return record;
    }
}
