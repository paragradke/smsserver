package com.example.sms.services.impl;


import com.example.sms.services.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> template;

    private static final Logger logger = LogManager.getLogger(SMSServiceImpl.class);

    public String getCacheEntry(final String key) {
        return (String) template.opsForValue().get(key);
    }

    public void setCacheEntry(final String key, final String cacheEntry) {
        template.opsForValue().set(key, cacheEntry);

        // set a expire for a message
        template.expire(key, 4, TimeUnit.HOURS);
        logger.info("Saved entry to cache  key :"+ key);
    }

    @Override
    public Integer getSLAEntry(String key) {
        logger.info("getSLAEntry : Key =" + key);
        String slaEntry = (String) template.opsForValue().get(key);
        logger.info("getSLAEntry : slaEntry =" + slaEntry);
        return Integer.parseInt(slaEntry);
    }

    @Override
    public void setSLAEntry(String key, Integer slaEntry) {
        template.opsForValue().set(key, slaEntry);

        // set a expire for a message
        template.expire(key, 1, TimeUnit.DAYS);
        logger.info("Saved entry to SLA key :"+ key);
    }
}
