package com.example.authproject.services;


import com.example.authproject.cache.CacheEntry;
import com.example.authproject.cache.SLAEntry;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {

    public CacheEntry getCacheEntry(final String key);

    public void setCacheEntry(final String key, final CacheEntry cacheEntry);

    public SLAEntry getSLAEntry(final String key);

    public void setSLAEntry(final String key, final SLAEntry slaEntry);
}
