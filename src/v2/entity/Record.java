package v2.entity;

/**
 * Created by Fomichev Yuri on 24.06.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Record {
    private static long recordsCounter;

    private Parameter timestamp = new Parameter<String>("Timestamp");
    private Parameter referer = new Parameter<String>("Referer");
    private Parameter method = new Parameter<String>("Method");
    private Parameter url = new Parameter<String>("URL");
    private Parameter query = new Parameter<String>("Query");
    private Parameter status = new Parameter<Integer>("Status");
    private Parameter size = new Parameter<Long>("Size");
    private Parameter time = new Parameter<Long>("Time");
    private Parameter userName = new Parameter<String>("UserName");
    private Parameter userID = new Parameter<Integer>("UserID");
    private Parameter sessionID = new Parameter<String>("SessionID");

    public Record() {
        recordsCounter += 1;
    }

    public Parameter getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp.setValue(timestamp);
    }

    public Parameter getReferer() {
        return referer;
    }

    public void setReferer(Object referer) {
        this.referer.setValue(referer);
    }

    public Parameter getMethod() {
        return method;
    }

    public void setMethod(Object method) {
        this.method.setValue(method);
    }

    public Parameter getURL() {
        return url;
    }

    public void setURL(Object url) {
        this.url.setValue(url);
    }

    public Parameter getQuery() {
        return query;
    }

    public void setQuery(Object query) {
        this.query.setValue(query);
    }

    public Parameter getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status.setValue(status);
    }

    public Parameter getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size.setValue(size);
    }

    public Parameter getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time.setValue(time);
    }

    public Parameter getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName.setValue(userName);
    }

    public Parameter getUserID() {
        return userID;
    }

    public void setUserID(Object userID) {
        this.userID.setValue(userID);
    }

    public Parameter getSessionID() {
        return sessionID;
    }

    public void setSessionID(Object sessionID) {
        this.sessionID.setValue(sessionID);
        ;
    }

    public static long getRecordsCounter() {
        return recordsCounter;
    }

    @Override
    public String toString() {
        return "Record{" +
                timestamp + ", " +
                referer + ", " +
                method + ", " +
                url + ", " +
                query + ", " +
                status + ", " +
                size + ", " +
                time + ", " +
                userName + ", " +
                userID + ", " +
                sessionID + '}';
    }
}
