package com.example.sms.services;



import org.springframework.stereotype.Service;

@Service
public interface RedisService {

    public String getCacheEntry(final String key);

    public void setCacheEntry(final String key, final String cacheEntry);

    public Integer getSLAEntry(final String key);

    public void setSLAEntry(final String key, final Integer slaEntry);
}
