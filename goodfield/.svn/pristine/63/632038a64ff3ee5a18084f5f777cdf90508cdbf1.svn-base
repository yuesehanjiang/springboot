package com.alibaba.util;

import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import cn.jpush.api.JPushClient;

import com.alibaba.controller.UserController;

public class RedisUtils {
static 	Logger logger = Logger.getLogger(UserController.class);



	public static Jedis getJedis() {
		/*Jedis	jedis = new Jedis("r-wz95ce79fc4f9fd4762.redis.rds.aliyuncs.com");

		jedis.auth("GDfl1234");
*/
		
		Jedis	jedis = new Jedis("111.230.9.75", 6379);
		  jedis.auth("123");
		
		return jedis;
	}
	
	

	/**
	 * 获取hash表中所有key
	 * 
	 * @param name
	 * @return r-wz99c0368fd88b64.redis.rds.aliyuncs.com   RRttcch123
	 */
	public static Set<String> getHashAllKey(String name) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			
			/*  jedis = new Jedis("111.230.9.75", 6379);
			  jedis.auth("123");*/
			 
			return jedis.hkeys(name);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	
	
	   public static void main(String[] args) {
		   Jedis jedis = getJedis();
		   System.out.println(jedis);
	}

	/**
	 * 从redis hash表中获取
	 * 
	 * @param hashName
	 * @param key
	 * @return
	 */
	public static String getHashKV(String hashName, String key) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.hget(hashName, key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 删除hash表的键值对
	 * 
	 * @param hashName
	 * @param key
	 */
	public static Long delHashKV(String hashName, String key) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.hdel(hashName, key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 存放hash表键值对
	 * 
	 * @param hashName
	 * @param key
	 * @param value
	 */
	public static Long setHashKV(String hashName, String key, String value) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.hset(hashName, key, value);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 删除键值对
	 * 
	 * @param k
	 * @return
	 */
	public static Long delKV(String k) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.del(k);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 放键值对 永久
	 * 
	 * @param k
	 * @param v
	 */
	public static String setKV(String k, String v) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.set(k, v);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 放键值对 设置过期时间
	 * 
	 * @param k
	 * @param v
	 */
	public static String setKV(String k, int second, String v) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.setex(k, second, v);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 根据key取value
	 *
	 * @param k
	 * @return
	 */
	public static String getKV(String k) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.get(k);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 管理员-根据key取value
	 *
	 * @param k
	 * @return
	 */
	public static Set<String> getAdminKVLikes() {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.keys("*adminToken_*");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}



	/**
	 * 用户-根据key取value
	 *
	 * @param k
	 * @return
	 */
	public static Set<String> getUserKVLikes(String key) {
		Jedis jedis = null;
		try {
			 jedis = getJedis();
			return jedis.keys("*userToken_" + key + "_*");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

}
