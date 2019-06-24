package analytics;

import analytics.entity.Record;
import analytics.service.Service;

import java.io.File;
import java.io.IOException;
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

        System.out.print("Processing... ");

        while (scanner.hasNextLine()) {
            String textLine = scanner.nextLine();
            Record record = Service.getRecord(textLine);

            if (record != null) {
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
                overallCounter += 1;
            }
        }

//        statistics = Service.collectStatistics(statistics, dwr);
//
//        HtmlCreator.create(sortStatistics(new ArrayList<>(statistics.values()), SORT_METHOD), OUTPUT_FILE, "DWR: Sorted by [" + SORT_METHOD + "]. Processed " + overallCounter + " records");

        System.out.printf("Done in %d seconds, %s records processed.%n", ((int) (((System.nanoTime() - start) / 1_000_000_000))), overallCounter);
    }
}