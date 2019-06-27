package analytics;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Scanner;

public class App {
    private final static String PATH = "c://";
    private final static String SORT_METHOD = "calls"; /* avg-time, calls */
    private final static String INPUT_FILE = PATH.concat("rh-localhost_access.2019-06-21.log");
    private final static String OUTPUT_FILE = PATH.concat("rh-localhost_access.2019-06-21-dwr-").concat(SORT_METHOD).concat(".html");

    private final static int MAX_SIZE = 500_000;
    private final static boolean SKIP_BLANK = true;

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        File file = new File(INPUT_FILE);
        Scanner scanner = new Scanner(file);
        int overallCounter = 0;
        StringBuilder stringBuilder = new StringBuilder();

        LocalDate date = LocalDate.of(1983, Month.MAY, 9);
        LocalTime time = LocalTime.of(0, 0, 13);
        LocalTime time2 = time.plusSeconds(47);
        System.out.println(date);
        System.out.println(time);
        System.out.println(time2);

//        for (int hour = 0, end = 23; hour <= end; hour += 1) {
//            stringBuilder
//                    .append((hour < 10) ? ("0" + hour) : String.valueOf(hour))
//                    .append(":")
//                    .append("00")
//                    .append(":")
//                    .append("00")
//                    .append(".")
//                    .append("000");
//
//            String parseDate = stringBuilder.toString();
//
//            System.out.printf("hour: %2d, parseDate: %s%n", hour, parseDate);
//            stringBuilder.setLength(0);
//        }

//        System.out.print("Processing... ");
//
//        while (scanner.hasNextLine()) {
//            String textLine = scanner.nextLine();
//            Record record = Service.getRecord(textLine);
//
//            if (record != null) {
//                System.out.println();
//                System.out.println(record.getTimestamp().getValue());
//                System.out.println(((Date) record.getTimestamp().getValue()).getTime());
//                System.out.println(record.getTimestamp().getRaw());
//                overallCounter += 1;
//            }
//        }

//            String url = record.getUrl().getValue();
//            if (url.endsWith(".dwr")) {
//                if (dwrCounter <= MAX_SIZE) {
//                    dwrCounter = Service.addToMap(dwr, url, record, dwrCounter);
//                } else {
//                    statistics = Service.collectStatistics(statistics, dwr);
//                    dwrCounter = Service.clearMap(dwr);
//                }
//            }
//

//        statistics = Service.collectStatistics(statistics, dwr);
//
//        HtmlCreator.create(sortStatistics(new ArrayList<>(statistics.values()), SORT_METHOD), OUTPUT_FILE, "DWR: Sorted by [" + SORT_METHOD + "]. Processed " + overallCounter + " records");

        System.out.printf("Done in %d seconds, %s records processed.%n", ((int) (((System.nanoTime() - start) / 1_000_000_000))), overallCounter);
    }
}