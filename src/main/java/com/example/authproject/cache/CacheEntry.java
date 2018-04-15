package com.example.authproject.cache;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

//@RedisHash("CacheEntry")
public class CacheEntry implements Serializable {

    public CacheEntry(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    private String to;
    private String from;
}
