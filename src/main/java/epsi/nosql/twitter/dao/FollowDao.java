package epsi.nosql.twitter.dao;

import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;

public class FollowDao {

    private final static Logger log = Logger.getLogger(FollowDao.class);

    public static void addFollow(String user, String idFollow){
        String key = Constantes.USER_KEY_FIELD + user + Constantes.FOLLOWING_KEY_FIELD;

        log.info(user + " follow maintenant "+ idFollow);

        RedisDao.getJedis().rpush(key,idFollow);
    }

    public static void addFollower(String user, String idFollower){
        String key = Constantes.USER_KEY_FIELD + user + Constantes.FOLLOWER_KEY_FIELD;

        log.info(user + " est follow par "+ idFollower);

        RedisDao.getJedis().rpush(key,idFollower);
    }

    public static List<String> getFollower(String user){
        Jedis jedis = RedisDao.getJedis();

        String key = Constantes.USER_KEY_FIELD + user + Constantes.FOLLOWER_KEY_FIELD;

        return jedis.lrange(key, 0, jedis.llen(key));
    }

    public static List<String> getFollowing(String user) {
        Jedis jedis = RedisDao.getJedis();

        String key = Constantes.USER_KEY_FIELD + user + Constantes.FOLLOWING_KEY_FIELD;

        return jedis.lrange(key, 0, jedis.llen(key));
    }

}
