package epsi.nosql.twitter.dao;

import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TweetDao {

    private final static Logger log = Logger.getLogger(TweetDao.class);

    public static void newTweet(String login, String tweetMsg) {
        Jedis jedis = RedisDao.getJedis();

        String defTweet = login + " a tweeté : <br>" + tweetMsg;

        jedis.lpush(Constantes.USER_KEY_FIELD + login + Constantes.TWEETS_KEY_FIELD, defTweet);
        jedis.lpush(Constantes.USER_KEY_FIELD + login + Constantes.TWEETS_TODISPLAY_KEY_FIELD, defTweet);

        for(String lFol : FollowDao.getFollower(login)) {
            jedis.lpush(Constantes.USER_KEY_FIELD + lFol + Constantes.TWEETS_TODISPLAY_KEY_FIELD,defTweet);
        }

        log.info("Ajout du tweet : '"+tweetMsg+"' pour le user "+ login);
    }

    /**
     * Get all the tweets of a user
     * @param login
     * @return
     */
    public static List<String> getAllTweets(String login) {
        Jedis jedis = RedisDao.getJedis();

        String key = Constantes.USER_KEY_FIELD + login + Constantes.TWEETS_KEY_FIELD;

        log.info("Demande de tous les tweets de "+ login);

        return jedis.lrange(key ,0, jedis.llen(key));
    }

    /**
     * Get all the tweet to display of a user
     * @param login
     * @return
     */
    public static List<String> getAllTweetsToDisplay(String login) {
        Jedis jedis = RedisDao.getJedis();

        String key = Constantes.USER_KEY_FIELD + login + Constantes.TWEETS_TODISPLAY_KEY_FIELD;

        log.info("Demande de tous les tweets à afficher de "+ login);

        return jedis.lrange(key, 0, jedis.llen(key));
    }

    /**
     * Search a pattern in the lists of displayed tweets
     * @param loginUser
     * @param regex
     * @return
     */
    public static List<String> searchInTweet(String loginUser, String regex) {
        Jedis jedis = RedisDao.getJedis();

        String key = Constantes.USER_KEY_FIELD + loginUser + Constantes.TWEETS_TODISPLAY_KEY_FIELD;

        log.info("Recherche de "+ regex +" dans les tweets affichés de "+ loginUser);

        return jedis.lrange(key ,0, jedis.llen(key));
    }
}
