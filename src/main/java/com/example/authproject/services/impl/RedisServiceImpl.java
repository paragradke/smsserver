package com.example.authproject.services.impl;

import com.example.authproject.cache.CacheEntry;
import com.example.authproject.cache.SLAEntry;
import com.example.authproject.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> template;

    public CacheEntry getCacheEntry(final String key) {
        return (CacheEntry) template.opsForValue().get(key);
    }

    public void setCacheEntry(final String key, final CacheEntry cacheEntry) {
        template.opsForValue().set(key, cacheEntry);

        // set a expire for a message
        template.expire(key, 4, TimeUnit.HOURS);
    }

    @Override
    public SLAEntry getSLAEntry(String key) {
        return (SLAEntry) template.opsForValue().get(key);
    }

    @Override
    public void setSLAEntry(String key, SLAEntry slaEntry) {
        template.opsForValue().set(key, slaEntry);

        // set a expire for a message
        template.expire(key, 1, TimeUnit.DAYS);
    }
}
