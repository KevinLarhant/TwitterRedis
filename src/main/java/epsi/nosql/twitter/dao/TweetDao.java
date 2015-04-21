package epsi.nosql.twitter.dao;

import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TweetDao {

    private final static Logger log = Logger.getLogger(TweetDao.class);

    public static void newTweet(String login, String tweetMsg) {
        Jedis jedis = RedisDao.getJedis();

        jedis.rpush(Constantes.USER_KEY_FIELD + login + Constantes.CREDENTIALS_KEY_FIELD, tweetMsg);

        log.info("Ajout du tweet : '"+tweetMsg+"' pour le user "+ login);
    }

    public static List<String> getAllTweets(String login) {
        Jedis jedis = RedisDao.getJedis();

        String key = Constantes.USER_KEY_FIELD + login + Constantes.CREDENTIALS_KEY_FIELD;

        log.info("Demande de tous les tweets de "+ login);

        return jedis.lrange(key ,0, jedis.llen(key));
    }
}
