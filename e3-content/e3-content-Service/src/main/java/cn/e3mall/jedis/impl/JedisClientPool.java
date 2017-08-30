package cn.e3mall.jedis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.jedis.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 7.1.2.	单机版实现类
 * @author admin
 *
 */
public class JedisClientPool implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String ok = jedis.set(key, value);
		jedis.close();
		return ok;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean flag = jedis.exists(key);
		jedis.close();
		return flag;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long num = jedis.expire(key, seconds);
		jedis.close();
		//返回1.成功 0.失败
		return num;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long time = jedis.ttl(key);
		jedis.close();
		return time;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long num = jedis.incr(key);
		jedis.close();
		return num;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long num = jedis.hset(key, field, value);
		jedis.close();
		return num;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.hget(key, field);
		jedis.close();
		return value;
	}

	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = jedisPool.getResource();
		Long num = jedis.hdel(key, field);
		jedis.close();
		return num;
	}

}
