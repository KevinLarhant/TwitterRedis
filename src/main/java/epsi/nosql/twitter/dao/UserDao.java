package epsi.nosql.twitter.dao;

import epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
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
        jed.rpush(Constantes.USERS_KEY_FIELD, login);

        log.info("création user " + login + ", avec id : "+ idUser);
    }


    /**
     * Check if the credentials given by 'login' are correct
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
     * The first user gonna follow the second
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

    /**
     * Return the list of all users of the app
     * @return
     */
    public static List<String> getAllUsers() {
        Jedis jedis = RedisDao.getJedis();

        log.info("Recherche de tous les users");

        return jedis.lrange(Constantes.USERS_KEY_FIELD,0, jedis.llen(Constantes.USERS_KEY_FIELD));
    }

    /**
     * Verify if a user with this login already exists
     * @param login
     * @return
     */
    public static boolean checkExists(String login) {
        List<String> listUser = UserDao.getAllUsers();

        for(String user : listUser) {
            if(login.equals(user)){
                return true;
            }
        }

        return false;
    }
}
