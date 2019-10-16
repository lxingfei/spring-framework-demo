package com.leh.session;

import com.leh.util.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: leh
 * @Date: 2019/10/16 10:12
 * @Description:
 */
public class RedisSessionDao extends AbstractSessionDAO {

    private final String SHIRO_SESSION_PREFIX = "shiro-session:";
    @Resource
    private JedisUtil jedisUtil;

    /**
     * 通过二进制的形式存储 key value
     *
     * @return
     */
    private byte[] getKey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    private void saveSession(Session session){
        if(session == null || session.getId() == null){
            return;
        }
        byte[] key = getKey(session.getId().toString());
        byte[] value = SerializationUtils.serialize(session);
        jedisUtil.set(key, value);
        jedisUtil.expire(key, 60 * 60 * 60);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        //将sessionId和session绑定
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("read session");
        if (sessionId == null) {
            return null;
        }
        byte[] key = getKey(sessionId.toString());
        byte[] value = jedisUtil.get(key);
        return (Session) SerializationUtils.deserialize(value);
    }

    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    public void delete(Session session) {
        if(session == null || session.getId() == null){
            return;
        }
        byte[] key = getKey(session.getId().toString());

        jedisUtil.del(key);
    }


    /**
     * 获取存活的session
     * @return
     */
    public Collection<Session> getActiveSessions() {
        //先获取key值
        Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessionSet = new HashSet<Session>();
        if(CollectionUtils.isEmpty(keys)){
            return sessionSet;
        }
        for(byte[] key : keys){
            Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
            sessionSet.add(session);
        }
        return null;
    }
}
