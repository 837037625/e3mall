package cn.e3mall.jedis;

public interface JedisClient {
    /**
     * 设置String key-value
     */
	String set(String key, String value);
	/**
     * 根据String key 得到value
     */
	String get(String key);
	/**
     *  判断某key是否存在
     */
	Boolean exists(String key);
	/**
     * 设置某key的过期时间,单位秒
     */
	Long expire(String key, int seconds);
	/**
     * 可以查看key的过期时间,返回s
     */
	Long ttl(String key);
	/**
     *  key 中储存的数字值增一
     */
	Long incr(String key);
	/**
     *  存储hash key-字段-值
     */
	Long hset(String key, String field, String value);
	/**
     *  通过key-字段 得到hash值
     */
	String hget(String key, String field);
	/**
     *   批量删除hash key里面的字段
     */
	Long hdel(String key, String... field);

}
