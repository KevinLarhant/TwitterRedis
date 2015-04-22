package epsi.nosql.twitter.dao;

import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserDao {

    private static Logger log = Logger.getLogger(UserDao.class);

    /**
     * Create a new user
     * @param login
     * @param pwd
     */
    public static void newUser(String login, String pwd) {
        String idUser = UUID.randomUUID().toString();

        Map<String, String> mapUserCredentials = new HashMap<>();
        mapUserCredentials.put(Constantes.PASSWORD, pwd);
        mapUserCredentials.put(Constantes.ID_USER,idUser);

        Jedis jed = RedisDao.getJedis();
        jed.hmset(Constantes.USER_KEY_FIELD + login + Constantes.CREDENTIALS_KEY_FIELD, mapUserCredentials);

        log.info("création user " + login + ", avec id : "+ idUser);
    }


    /**
     * TODO :tout est en clair pour l'instant #yolo
     * @param login
     * @param pwd
     * @return l'id du user s'il existe, null sinon
     */
    public static String checkCredentials(String login, String pwd) {
        Jedis jedis = RedisDao.getJedis();
        String password = jedis.hget(Constantes.USER_KEY_FIELD + login + Constantes.CREDENTIALS_KEY_FIELD, Constantes.PASSWORD);

        if(password == null){
            log.info(login + " n'existe pas en base");
            return null;
        }

        if( password.equals(pwd)) {
            return jedis.hget(Constantes.USER_KEY_FIELD + login + Constantes.CREDENTIALS_KEY_FIELD, Constantes.ID_USER);
        }

        return null;
    }

    /**
     * Le premier user va follow le second
     * @param loginUser
     * @param loginFollowed
     * @return
     */
    public static void gonnaFollow(String loginUser, String loginFollowed) {
        Jedis jedis = RedisDao.getJedis();

        String keyUser1 = Constantes.USER_KEY_FIELD + loginUser + Constantes.FOLLOWING_KEY_FIELD;
        String keyUserFollowed = Constantes.USER_KEY_FIELD + loginFollowed + Constantes.FOLLOWING_KEY_FIELD;

        log.info(loginUser + " follow maintenant : " + loginFollowed);

        jedis.rpush(keyUser1, loginFollowed);
        jedis.rpush(keyUserFollowed, keyUser1);
    }
}
