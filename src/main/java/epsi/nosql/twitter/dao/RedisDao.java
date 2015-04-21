package epsi.nosql.twitter.dao;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Objects;

/**
 * Created by klarhant on 26/03/2015.
 */
public class RedisDao implements ServletContextListener {

    private static Jedis jedis;
    private static final Logger log = Logger.getLogger(RedisDao.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            jedis = new Jedis("localhost");
            log.info("Connected to Redis");
        } catch (Exception e) {
            log.error("Failed to connect to Redis");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        jedis.close();
        log.info("Connection to Redis closed");
    }


    public static void insert(String key, String value) {
        jedis.set(key, value);
    }

    public static void delete(String key) {
            jedis.del(key);
    }

    public static String getValue(String key) {
        return jedis.get(key);
    }

    public static Jedis getJedis() {
        return jedis;
    }
}
