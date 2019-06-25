package analytics.service;

import analytics.entity.parameter.Parameter;
import analytics.entity.Record;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Fomichev Yuri on 24.06.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Service {
    public static final String SPACE = " ";
    public static final String BLANK = "-";

//    public static Parameter getParameter(String value, boolean doZeroing) {
//        Parameter<String> parameter = new Parameter<>();
//        int index = value.indexOf(":");
//
//        parameter.setKey(value.substring(0, index));
//        parameter.setValue((doZeroing && value.endsWith("-")) ? "0" : value.substring(index + 1));
//
//        return parameter;
//    }

//    public static <T> Parameter getParameter(String key, T value, String raw) {
//        Parameter<T> parameter = new Parameter<>();
//
//        parameter.setKey(key);
//        parameter.setValue(value);
//        parameter.setRaw(raw);
//
//        return parameter;
//    }
//
//    public static <T> Parameter getParameter(String key, T value) {
//        Parameter<T> parameter = new Parameter<>();
//
//        parameter.setKey(key);
//        parameter.setValue(value);
//        parameter.setRaw(value.toString());
//
//        return parameter;
//    }
//
//    public static Parameter getParameter(String raw) {
//        try {
//            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
//            Date date = dateFormat.parse(raw.substring(1, (raw.length() - 1)));
//
//            return Service.getParameter("TIMESTAMP", date, raw);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static Parameter getParameter(String raw, boolean zero) {
//        int index = raw.indexOf(":");
//
//        Service.getParameter(raw.substring(0, index))
//
//        parameter.setKey(value.substring(0, index));
//        parameter.setValue((doZeroing && value.endsWith("-")) ? "0" : value.substring(index + 1));
//
//        return null;
//    }

    public static Record getRecord(String rawText) {
        int splitIndex = (rawText.indexOf("]") + 1);
        String timeStamp = rawText.substring(0, splitIndex++);
        List<String> list = Arrays.asList(rawText.substring(splitIndex).split("\\s"));

        if (list.get(0).equals(BLANK)) return null;

        Record record = new Record();
//        record.setTimestamp(Service.getParameter(timeStamp));
//        record.setReferer(Service.getParameter("REFERER", list.get(0)));
//        record.setMethod(Service.getParameter("METHOD", list.get(1)));
//        record.setUrl(Service.getParameter("URL", list.get(2)));
//        record.setStatus(Service.getParameter(list.get(3), false));
//            record.setSize(Service.getParameter(list.get(4), true));
//            record.setTime(Service.getParameter(list.get(5), true));
//            record.setUserName(Service.getParameter(list.get(6), false));
//            record.setUserID(Service.getParameter(list.get(7), false));
//            record.setSessionID(Service.getParameter(list.get(8), false));

        return record;
    }

//    public static Statistics createStatistics(String key, List<Record> records) {
//        Statistics statistics = new Statistics();
//        long overallTime = 0;
//        long overallSize = 0;
//        int minTime = Integer.parseInt(records.get(0).getTime().getValue(), 10);
//        int maxTime = minTime;
//        int minSize = Integer.parseInt(records.get(0).getSize().getValue(), 10);
//        int maxSize = minSize;
//
//        for (Record record : records) {
//            int time = Integer.parseInt(record.getTime().getValue(), 10);
//            int size = Integer.parseInt(record.getSize().getValue(), 10);
//
//            minTime = ((time < minTime) ? time : minTime);
//            maxTime = ((time > maxTime) ? time : maxTime);
//
//            overallTime += time;
//
//            minSize = ((size < minSize) ? size : minSize);
//            maxSize = ((size > maxSize) ? size : maxSize);
//
//            overallSize += size;
//        }
//
//        statistics.key = key;
//        statistics.calls = records.size();
//        statistics.overallTime = overallTime;
//        statistics.avgTime = (int) Math.round(Math.ceil(statistics.overallTime / statistics.calls));
//        statistics.minTime = minTime;
//        statistics.maxTime = maxTime;
//        statistics.overallSize = overallSize;
//        statistics.avgSize = (int) Math.round(Math.ceil(statistics.overallSize / statistics.calls));
//        statistics.minSize = minSize;
//        statistics.maxSize = maxSize;
//        statistics.records = records;
//
//        return statistics;
//    }
//
//    public static Statistics updateStatistics(String key, List<Record> records, Statistics prev) {
//        Statistics statistics = createStatistics(key, records);
//        List<Record> updatedList = prev.records;
//
//        statistics.calls += prev.calls;
//        statistics.overallTime += prev.overallTime;
//        statistics.avgTime = (int) Math.round(Math.ceil(statistics.overallTime / statistics.calls));
//        statistics.minTime = ((statistics.minTime < prev.minTime) ? statistics.minTime : prev.minTime);
//        statistics.maxTime = ((statistics.maxTime > prev.maxTime) ? statistics.maxTime : prev.maxTime);
//        statistics.overallSize += prev.overallSize;
//        statistics.avgSize = (int) Math.round(Math.ceil(statistics.overallSize / statistics.calls));
//        statistics.minSize = ((statistics.minSize < prev.minSize) ? statistics.minSize : prev.minSize);
//        statistics.maxSize = ((statistics.maxSize > prev.maxSize) ? statistics.maxSize : prev.maxSize);
//
//        if ((statistics.records != null) && (statistics.records.size() > 0)) {
//            updatedList.addAll(statistics.records);
//        }
//
//        statistics.records = updatedList;
//
//        return statistics;
//    }
//
//    private static int addToMap(Map<String, List<Record>> map, String key, Record record, int counter) {
//        List<Record> list = (map.containsKey(key) ? map.get(key) : new ArrayList<>());
//
//        list.add(record);
//        map.put(key, list);
//
//        return ++counter;
//    }
//
//    private static int clearMap(Map<String, List<Record>> map) {
//        map.clear();
//
//        return 0;
//    }
//
//    private static Map<String, Statistics> collectStatistics(Map<String, Statistics> storage, Map<String, List<Record>> portion) {
//        for (Map.Entry<String, List<Record>> entry : portion.entrySet()) {
//            String key = entry.getKey();
//
//            if (storage.containsKey(key)) {
//                Statistics prev = storage.get(key);
//                storage.put(key, Service.updateStatistics(key, entry.getValue(), prev));
//            } else {
//                storage.put(key, Service.createStatistics(key, entry.getValue()));
//            }
//        }
//
//        return storage;
//    }
//
//    private static List<Statistics> sortStatistics(List<Statistics> statistics, String mode) {
//        statistics.sort((a, b) -> {
//            if (mode.equals("avg-time")) {
//                if (a.getAvgTime() < b.getAvgTime()) return 1;
//                else if (a.getAvgTime() > b.getAvgTime()) return -1;
//            } else if (mode.equals("calls")) {
//                if (a.getCalls() < b.getCalls()) return 1;
//                else if (a.getCalls() > b.getCalls()) return -1;
//            }
//
//            return 0;
//        });
//
//        return statistics;
//    }
//
//    public static void create(List<Statistics> statistics, String fileName, String description) throws IOException {
//        final String TAB = "  ";
//        final String LINE_BREAK = System.lineSeparator();
//        FileWriter fileWriter = new FileWriter(fileName);
//        StringBuilder stringBuilder = new StringBuilder();
//        int index = 1;
//
//        stringBuilder /* open */
//                .append("<html>").append(LINE_BREAK)
//                .append(TAB).append("<body>").append(LINE_BREAK)
//                .append(TAB).append("<h2>").append(fileName).append("</h2>").append(LINE_BREAK)
//                .append(TAB).append("<h2>").append(description).append("</h2>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append("<table border='1'>").append(LINE_BREAK);
//
//        stringBuilder /* table header */
//                .append(TAB).append(TAB).append(TAB).append("<tr>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("#").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th>").append("Key").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Calls").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Avg Time").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Min Time").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Max Time").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Overall Time").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Avg Size").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Min Size").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Max Size").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Overall Size").append("</th>").append(LINE_BREAK)
//                .append(TAB).append(TAB).append(TAB).append("</tr>").append(LINE_BREAK);
//
//        for (Statistics statistic : statistics) {
//            stringBuilder /* table row */
//                    .append(TAB).append(TAB).append(TAB).append("<tr>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(index++).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td>").append(statistic.getKey()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getCalls()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getAvgTime()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMinTime()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMaxTime()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getOverallTime()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getAvgSize()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMinSize()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMaxSize()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getOverallSize()).append("</td>").append(LINE_BREAK)
//                    .append(TAB).append(TAB).append(TAB).append("</tr>").append(LINE_BREAK);
//        }
//
//        stringBuilder /* close */
//                .append(TAB).append(TAB).append("</table>").append(LINE_BREAK)
//                .append(TAB).append("</body>").append(LINE_BREAK)
//                .append("</html>").append(LINE_BREAK);
//
//        fileWriter.write(stringBuilder.toString());
//        fileWriter.flush();
//        fileWriter.close();
//    }
}
