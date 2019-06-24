package analytics.entity;

import analytics.service.Service;

import java.util.List;

/**
 * Created by Fomichev Yuri on 24.06.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Statistics {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCalls() {
        return calls;
    }

    public void setCalls(Integer calls) {
        this.calls = calls;
    }

    public Long getOverallTime() {
        return overallTime;
    }

    public void setOverallTime(Long overallTime) {
        this.overallTime = overallTime;
    }

    public Integer getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Integer avgTime) {
        this.avgTime = avgTime;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getAvgSize() {
        return avgSize;
    }

    public void setAvgSize(Integer avgSize) {
        this.avgSize = avgSize;
    }

    public Long getOverallSize() {
        return overallSize;
    }

    public void setOverallSize(Long overallSize) {
        this.overallSize = overallSize;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        final String space = Service.SPACE;
        StringBuilder stringBuilder = new StringBuilder(19);

        stringBuilder
                .append("KEY").append(":").append(key).append(space)
                .append("CALLS").append(":").append(calls).append(space)
                .append("AVG_TIME").append(":").append(avgTime).append(space)
                .append("MIN_TIME").append(":").append(minTime).append(space)
                .append("MAX_TIME").append(":").append(maxTime).append(space)
                .append("OVERALL_TIME").append(":").append(overallTime).append(space)
                .append("AVG_SIZE").append(":").append(avgSize).append(space)
                .append("MIN_SIZE").append(":").append(minSize).append(space)
                .append("MAX_SIZE").append(":").append(maxSize).append(space)
                .append("OVERALL_SIZE").append(":").append(overallSize).append(space);

        return stringBuilder.toString();
    }
}
