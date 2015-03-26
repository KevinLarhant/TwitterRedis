package epsi.nosql.twitter.dao;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by klarhant on 26/03/2015.
 */
public class RedisDao implements ServletContextListener {

    private static Jedis jedis;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        jedis = new Jedis("localhost");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
