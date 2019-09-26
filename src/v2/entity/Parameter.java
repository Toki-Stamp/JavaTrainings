package v2.entity;

/**
 * Created by Fomichev Yuri on 24.06.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Parameter<T> {
    private String key;
    private T value;

    public Parameter(String key) {
        this.key = key;
    }

    public Parameter(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + ((value != null) ? (":" + value.toString()) : "");
    }
}
