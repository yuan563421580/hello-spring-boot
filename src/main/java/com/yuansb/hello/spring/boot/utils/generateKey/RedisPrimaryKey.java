package com.yuansb.hello.spring.boot.utils.generateKey;

import com.yuansb.hello.spring.boot.utils.RedisUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Component
public class RedisPrimaryKey {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//年月日时分秒格式

    DecimalFormat df = new DecimalFormat("000000");//补充位数为6位

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成 16位 key
     * @param keyType
     * @return
     */
    public synchronized String generateKey(String keyType) {
        String autoKey = keyType + "_AUTO_KEY";
        Long autoKeyValue = redisUtils.incr(autoKey);
        if (autoKeyValue > 999999L) {
            redisUtils.set(autoKey, "0");
            autoKeyValue = redisUtils.incr(autoKey);
        }
        String autoKeyValueStr = String.valueOf(autoKeyValue);//定义接收用于自增的value
        return sdf.format(new Date()) + df.format(Integer.parseInt(autoKeyValueStr));
    }

}
