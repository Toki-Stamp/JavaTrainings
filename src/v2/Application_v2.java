package v2;

import v2.entity.Parameter;
import v2.entity.Record;
import v2.service.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Fomichev Yuri on 25.09.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Application_v2 {
    private final static String PATH = "c://";
    private final static String FILE_NAME = "localhost_access.2019-09-26";
    private final static String FILE_EXT = ".log";

    public static void main(String[] args) {
        File file = new File(PATH.concat(FILE_NAME).concat(FILE_EXT));
        List<Record> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Record record = Service.createRecord(line);

                list.add(record);
            }

            System.out.printf("Records: %s, Size: %s%n", Record.getRecordsCounter(), list.size());

            for (Record record : list) {
                System.out.println(record.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
