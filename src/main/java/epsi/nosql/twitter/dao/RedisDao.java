package epsi.nosql.twitter.dao;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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

            if(!UserDao.checkExists("admin")) {
                log.info("creation admin");
                UserDao.newUser("admin", "admin");
            }
        } catch (Exception e) {
            log.error("Failed to connect to Redis");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        jedis.close();
        log.info("Connection to Redis closed");
    }


    public static Jedis getJedis() {
        return jedis;
    }
}
