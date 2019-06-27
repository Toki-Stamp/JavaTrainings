package analytics.entity;

/**
 * Created by Fomichev Yuri on 24.06.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Parameter<T> {
    private String key;
    private String raw;
    private T value;

    public Parameter(String key, String raw, T value) {
        this.key = key;
        this.raw = raw;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(key)
                .append(":")
                .append(value.toString());

        if (raw != null) {
            stringBuilder
                    .append("[RAW:")
                    .append(raw)
                    .append("]");
        }

        return stringBuilder.toString();
    }
}
