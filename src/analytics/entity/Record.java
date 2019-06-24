package analytics.entity;

import analytics.service.Service;

import java.util.List;

/**
 * Created by Fomichev Yuri on 24.06.2019
 * Contact me at : toki.stamp@gmail.com
 */

public class Record {
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

    public Record() {
        id = Service.getParameter("ID", Integer.toString(++recordsCounter, 10));
    }

    public Parameter getId() {
        return id;
    }

    public Parameter getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Parameter timestamp) {
        this.timestamp = timestamp;
    }

    public Parameter getReferer() {
        return referer;
    }

    public void setReferer(Parameter referer) {
        this.referer = referer;
    }

    public Parameter getMethod() {
        return method;
    }

    public void setMethod(Parameter method) {
        this.method = method;
    }

    public Parameter getUrl() {
        return url;
    }

    public void setUrl(Parameter url) {
        this.url = url;
    }

    public Parameter getStatus() {
        return status;
    }

    public void setStatus(Parameter status) {
        this.status = status;
    }

    public Parameter getSize() {
        return size;
    }

    public void setSize(Parameter size) {
        this.size = size;
    }

    public Parameter getTime() {
        return time;
    }

    public void setTime(Parameter time) {
        this.time = time;
    }

    public Parameter getUserName() {
        return userName;
    }

    public void setUserName(Parameter userName) {
        this.userName = userName;
    }

    public Parameter getUserID() {
        return userID;
    }

    public void setUserID(Parameter userID) {
        this.userID = userID;
    }

    public Parameter getSessionID() {
        return sessionID;
    }

    public void setSessionID(Parameter sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public String toString() {
        final String space = Service.SPACE;
        StringBuilder stringBuilder = new StringBuilder(25);

        stringBuilder
                .append(id).append(space)
                .append(timestamp).append(space)
                .append(referer).append(space)
                .append(method).append(space)
                .append(url).append(space)
                .append(status).append(space)
                .append(size).append(space)
                .append(time).append(space)
                .append(userName).append(space)
                .append(userID).append(space)
                .append(sessionID);

        return stringBuilder.toString();
    }
}
