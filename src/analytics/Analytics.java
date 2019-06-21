package analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Analytics {
    private final static String PATH = "c://";
    private final static String SORT_METHOD = "avg-time"; /* avg-time, calls */
    private final static String INPUT_FILE = PATH.concat("rh-localhost_access.2019-06-20.log");
    private final static String OUTPUT_FILE = PATH.concat("rh-localhost_access.2019-06-20-dwr-").concat(SORT_METHOD).concat(".html");
    private final static String BLANK = "-";
    private final static int MAX_SIZE = 500_000;
    private final static boolean SKIP_BLANK = true;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        File file = new File(INPUT_FILE);
        Scanner scanner = new Scanner(file);

        Map<String, List<Record>> dwr = new HashMap<>(MAX_SIZE);
        int dwrCounter = 0;
//        Map<String, List<Record>> pages = new HashMap<>(MAX_SIZE);
//        int pagesCounter = 0;
//        Map<String, List<Record>> other = new HashMap<>(MAX_SIZE);
//        int otherCounter = 0;

        Map<String, Statistics> statistics = new HashMap<>();

        int overallCounter = 0;

        System.out.print("Processing... ");

        while (scanner.hasNextLine()) {
            String raw = scanner.nextLine().replace("#page- ", "#page-");
            List<String> line = Arrays.asList(raw.split("\\s"));

            if (SKIP_BLANK && line.get(2).equals(BLANK)) continue;

            Record record = Record.getRecord(line);

            String url = record.getUrl().getValue();

            if (url.endsWith(".dwr")) {
                if (dwrCounter <= MAX_SIZE) {
                    dwrCounter = addToMap(dwr, url, record, dwrCounter);
                } else {
                    statistics = collectStatistics(statistics, dwr);
                    dwrCounter = clearMap(dwr);
                }
            }

            overallCounter += 1;
        }

        statistics = collectStatistics(statistics, dwr);

//        HtmlCreator.create(sortStatistics(new ArrayList<>(statistics.values()), SORT_METHOD), OUTPUT_FILE, "DWR: Sorted by [" + SORT_METHOD + "]. Processed " + overallCounter + " records");

        System.out.printf("Done in %d seconds, %s records processed.%n", ((int) (((System.nanoTime() - start) / 1_000_000_000))), overallCounter);

        List<Record> list = statistics.get("/dwr/call/plaincall/objectsService.findAddressAutocomplete.dwr").getRecords();

        for (int i = 0, size = list.size(); i < size; i += 1) {
            Record rec = list.get(i);
            System.out.printf("%4s %-14s %s%n", i, rec.getTime(), rec);
        }
    }

    private static int addToMap(Map<String, List<Record>> map, String key, Record record, int counter) {
        List<Record> list = (map.containsKey(key) ? map.get(key) : new ArrayList<>());

        list.add(record);
        map.put(key, list);

        return ++counter;
    }

    private static int clearMap(Map<String, List<Record>> map) {
        map.clear();

        return 0;
    }

    private static Map<String, Statistics> collectStatistics(Map<String, Statistics> storage, Map<String, List<Record>> portion) {
        for (Map.Entry<String, List<Record>> entry : portion.entrySet()) {
            String key = entry.getKey();

            if (storage.containsKey(key)) {
                Statistics prev = storage.get(key);
                storage.put(key, Statistics.updateStatistics(key, entry.getValue(), prev));
            } else {
                storage.put(key, Statistics.createStatistics(key, entry.getValue()));
            }
        }

        return storage;
    }

    private static List<Statistics> sortStatistics(List<Statistics> statistics, String mode) {
        statistics.sort((a, b) -> {
            if (mode.equals("avg-time")) {
                if (a.getAvgTime() < b.getAvgTime()) return 1;
                else if (a.getAvgTime() > b.getAvgTime()) return -1;
            } else if (mode.equals("calls")) {
                if (a.getCalls() < b.getCalls()) return 1;
                else if (a.getCalls() > b.getCalls()) return -1;
            }

            return 0;
        });

        return statistics;
    }
}

class Record {
    final private static String SPACE = " ";
    private static int recordsCounter;
    private Parameter id;
    private Parameter timestamp;
    private Parameter referer;
    private Parameter method;
    private Parameter url;
    private Parameter status;
    private Parameter size;
    private Parameter time;
    private Parameter userName;
    private Parameter userID;
    private Parameter sessionID;

    private Record() {/*empty*/}

    public static Record getRecord(List<String> list) {
        Record record = new Record();

        record.id = Parameter.getParameter("ID", Integer.toString(recordsCounter++, 10));
        record.timestamp = Parameter.getParameter("TIMESTAMP", list.get(0).concat(SPACE).concat(list.get(1)));
        record.referer = Parameter.getParameter("REFERER", list.get(2));
        record.method = Parameter.getParameter("METHOD", list.get(3));
        record.url = Parameter.getParameter("URL", list.get(4));
        record.status = Parameter.getParameter(list.get(5), false);
        record.size = Parameter.getParameter(list.get(6), true);
        record.time = Parameter.getParameter(list.get(7), true);
        record.userName = Parameter.getParameter(list.get(8), false);
        record.userID = Parameter.getParameter(list.get(9), false);
        record.sessionID = Parameter.getParameter(list.get(10), false);

        return record;
    }

    public Parameter getID() {
        return id;
    }

    public Parameter getTimestamp() {
        return timestamp;
    }

    public Parameter getReferer() {
        return referer;
    }

    public Parameter getMethod() {
        return method;
    }

    public Parameter getUrl() {
        return url;
    }

    public Parameter getStatus() {
        return status;
    }

    public Parameter getSize() {
        return size;
    }

    public Parameter getTime() {
        return time;
    }

    public Parameter getUserName() {
        return userName;
    }

    public Parameter getUserID() {
        return userID;
    }

    public Parameter getSessionID() {
        return sessionID;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);

        stringBuilder
                .append(id).append(SPACE)
                .append(timestamp).append(SPACE)
                .append(referer).append(SPACE)
                .append(method).append(SPACE)
                .append(url).append(SPACE)
                .append(status).append(SPACE)
                .append(size).append(SPACE)
                .append(time).append(SPACE)
                .append(userName).append(SPACE)
                .append(userID).append(SPACE)
                .append(sessionID);

        return stringBuilder.toString();
    }
}

class Parameter {
    private String key;
    private String value;

    private Parameter() {/* empty */}

    public static Parameter getParameter(String value, boolean doZeroing) {
        Parameter parameter = new Parameter();
        int index = value.indexOf(":");

        try {
            parameter.key = value.substring(0, index);
            parameter.value = ((doZeroing && value.endsWith("-")) ? "0" : value.substring(index + 1));
        } catch (Exception e) {
            System.out.println(value);
            e.printStackTrace();
        }


        return parameter;
    }

    public static Parameter getParameter(String key, String value) {
        Parameter parameter = new Parameter();

        parameter.key = key;
        parameter.value = value;

        return parameter;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key.concat(":").concat(value);
    }
}

class Statistics {
    final private static String SPACE = " ";
    private String key;
    private Integer calls;
    private Long overallTime;
    private Integer avgTime;
    private Integer minTime;
    private Integer maxTime;
    private Integer minSize;
    private Integer maxSize;
    private Integer avgSize;
    private Long overallSize;
    private List<Record> records;

    public static Statistics createStatistics(String key, List<Record> records) {
        Statistics statistics = new Statistics();
        long overallTime = 0;
        long overallSize = 0;
        int minTime = Integer.parseInt(records.get(0).getTime().getValue(), 10);
        int maxTime = minTime;
        int minSize = Integer.parseInt(records.get(0).getSize().getValue(), 10);
        int maxSize = minSize;

        for (Record record : records) {
            int time = Integer.parseInt(record.getTime().getValue(), 10);
            int size = Integer.parseInt(record.getSize().getValue(), 10);

            minTime = ((time < minTime) ? time : minTime);
            maxTime = ((time > maxTime) ? time : maxTime);

            overallTime += time;

            minSize = ((size < minSize) ? size : minSize);
            maxSize = ((size > maxSize) ? size : maxSize);

            overallSize += size;
        }

        statistics.key = key;
        statistics.calls = records.size();
        statistics.overallTime = overallTime;
        statistics.avgTime = (int) Math.round(Math.ceil(statistics.overallTime / statistics.calls));
        statistics.minTime = minTime;
        statistics.maxTime = maxTime;
        statistics.overallSize = overallSize;
        statistics.avgSize = (int) Math.round(Math.ceil(statistics.overallSize / statistics.calls));
        statistics.minSize = minSize;
        statistics.maxSize = maxSize;
        statistics.records = records;

        return statistics;
    }

    public static Statistics updateStatistics(String key, List<Record> records, Statistics prev) {
        Statistics statistics = createStatistics(key, records);
        List<Record> updatedList = prev.records;

        statistics.calls += prev.calls;
        statistics.overallTime += prev.overallTime;
        statistics.avgTime = (int) Math.round(Math.ceil(statistics.overallTime / statistics.calls));
        statistics.minTime = ((statistics.minTime < prev.minTime) ? statistics.minTime : prev.minTime);
        statistics.maxTime = ((statistics.maxTime > prev.maxTime) ? statistics.maxTime : prev.maxTime);
        statistics.overallSize += prev.overallSize;
        statistics.avgSize = (int) Math.round(Math.ceil(statistics.overallSize / statistics.calls));
        statistics.minSize = ((statistics.minSize < prev.minSize) ? statistics.minSize : prev.minSize);
        statistics.maxSize = ((statistics.maxSize > prev.maxSize) ? statistics.maxSize : prev.maxSize);

        if ((statistics.records != null) && (statistics.records.size() > 0)) {
            updatedList.addAll(statistics.records);
        }

        statistics.records = updatedList;

        return statistics;
    }

    public String getKey() {
        return key;
    }

    public Integer getCalls() {
        return calls;
    }

    public Long getOverallTime() {
        return overallTime;
    }

    public Integer getAvgTime() {
        return avgTime;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public Integer getAvgSize() {
        return avgSize;
    }

    public Long getOverallSize() {
        return overallSize;
    }

    public List<Record> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(19);

        stringBuilder
                .append("KEY").append(":").append(key).append(SPACE)
                .append("CALLS").append(":").append(calls).append(SPACE)
                .append("AVG_TIME").append(":").append(avgTime).append(SPACE)
                .append("MIN_TIME").append(":").append(minTime).append(SPACE)
                .append("MAX_TIME").append(":").append(maxTime).append(SPACE)
                .append("OVERALL_TIME").append(":").append(overallTime).append(SPACE)
                .append("AVG_SIZE").append(":").append(avgSize).append(SPACE)
                .append("MIN_SIZE").append(":").append(minSize).append(SPACE)
                .append("MAX_SIZE").append(":").append(maxSize).append(SPACE)
                .append("OVERALL_SIZE").append(":").append(overallSize).append(SPACE);
//                .append("RECORDS_SIZE").append(":").append(records.size());

        return stringBuilder.toString();
    }
}

class HtmlCreator {
    public static void create(List<Statistics> statistics, String fileName, String description) throws IOException {
        final String TAB = "  ";
        final String LINE_BREAK = System.lineSeparator();
        FileWriter fileWriter = new FileWriter(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;

        stringBuilder /* open */
                .append("<html>").append(LINE_BREAK)
                .append(TAB).append("<body>").append(LINE_BREAK)
                .append(TAB).append("<h2>").append(fileName).append("</h2>").append(LINE_BREAK)
                .append(TAB).append("<h2>").append(description).append("</h2>").append(LINE_BREAK)
                .append(TAB).append(TAB).append("<table border='1'>").append(LINE_BREAK);

        stringBuilder /* table header */
                .append(TAB).append(TAB).append(TAB).append("<tr>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("#").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th>").append("Key").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Calls").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Avg Time").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Min Time").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Max Time").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Overall Time").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Avg Size").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Min Size").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Max Size").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append(TAB).append("<th align='center'>").append("Overall Size").append("</th>").append(LINE_BREAK)
                .append(TAB).append(TAB).append(TAB).append("</tr>").append(LINE_BREAK);

        for (Statistics statistic : statistics) {
            stringBuilder /* table row */
                    .append(TAB).append(TAB).append(TAB).append("<tr>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(index++).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td>").append(statistic.getKey()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getCalls()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getAvgTime()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMinTime()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMaxTime()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getOverallTime()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getAvgSize()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMinSize()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getMaxSize()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append(TAB).append("<td align='center'>").append(statistic.getOverallSize()).append("</td>").append(LINE_BREAK)
                    .append(TAB).append(TAB).append(TAB).append("</tr>").append(LINE_BREAK);
        }

        stringBuilder /* close */
                .append(TAB).append(TAB).append("</table>").append(LINE_BREAK)
                .append(TAB).append("</body>").append(LINE_BREAK)
                .append("</html>").append(LINE_BREAK);

        fileWriter.write(stringBuilder.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}