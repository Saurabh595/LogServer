package com.log.project.log.server.demo.forwarder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forwarder {

    public Forwarder(String hostName) {
        this.hostName = hostName;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String hostName;

    private long lastReadPosition;

    public Forwarder() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public long getLastReadPosition() {
        return lastReadPosition;
    }

    public void setLastReadPosition(long lastReadPosition) {
        this.lastReadPosition = lastReadPosition;
    }

    @Override
    public String toString() {
        return "Forwarder{" +
                "id=" + id +
                ", hostName='" + hostName + '\'' +
                ", lastReadPosition=" + lastReadPosition +
                '}';
    }
}

