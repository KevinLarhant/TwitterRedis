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


    public void insert(String key, String value) {
        jedis.set(key, value);
    }

    public void delete(String key) {
            jedis.del(key);
    }

    public String getValue(String key) {
        return jedis.get(key);
    }

    /**
     * TODO :tout est en clair pour l'instant #yolo
     * @param login
     * @param pwd
     * @return si les credentials sont vérifiés/présent en base ou pas
     */
    public static boolean checkCredentials(String login, String pwd) {
        String loginValue = jedis.get(login);

        if(loginValue == null){
            log.info(login + " n'existe pas en base");
            return false;
        }
        else {
            return loginValue.equals(pwd);
        }
    }
}
