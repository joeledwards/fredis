package fredis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.util.Slowlog;

public interface IRedis
{
	public RedisFuture<String> set(String key, String value);
	
	public RedisFuture<String> get(String key);
	
	public RedisFuture<Boolean> exists(String key);
	
	public RedisFuture<Long> persist(String key);
	
	public RedisFuture<String> type(String key);
	
	public RedisFuture<Long> expire(String key, int seconds);
	
	public RedisFuture<Long> expireAt(String key, long unixTime);
	
	public RedisFuture<Long> ttl(String key);
	
	public RedisFuture<Boolean> setbit(String key, long offset, boolean value);
	
	public RedisFuture<Boolean> setbit(String key, long offset, String value);
	
	public RedisFuture<Boolean> getbit(String key, long offset);
	
	public RedisFuture<Long> setrange(String key, long offset, String value);
	
	public RedisFuture<String> getrange(String key, long startOffset, long endOffset);
	
	public RedisFuture<String> getSet(String key, String value);
	
	public RedisFuture<Long> setnx(String key, String value);
	
	public RedisFuture<String> setex(String key, int seconds, String value);
	
	public RedisFuture<Long> decrBy(String key, long integer);
	
	public RedisFuture<Long> decr(String key);
	
	public RedisFuture<Long> incrBy(String key, long integer);
	
	public RedisFuture<Long> incr(String key);
	
	public RedisFuture<Long> append(String key, String value);
	
	public RedisFuture<String> substr(String key, int start, int end);
	
	public RedisFuture<Long> hset(String key, String field, String value);
	
	public RedisFuture<String> hget(String key, String field);
	
	public RedisFuture<Long> hsetnx(String key, String field, String value);
	
	public RedisFuture<String> hmset(String key, Map<String, String> hash);
	
	public RedisFuture<List<String>> hmget(String key, String... fields);
	
	public RedisFuture<Long> hincrBy(String key, String field, long value);
	
	public RedisFuture<Boolean> hexists(String key, String field);
	
	public RedisFuture<Long> hdel(String key, String... field);
	
	public RedisFuture<Long> hlen(String key);
	
	public RedisFuture<Set<String>> hkeys(String key);
	
	public RedisFuture<List<String>> hvals(String key);
	
	public RedisFuture<Map<String, String>> hgetAll(String key);
	
	public RedisFuture<Long> rpush(String key, String... string);
	
	public RedisFuture<Long> lpush(String key, String... string);
	
	public RedisFuture<Long> llen(String key);
	
	public RedisFuture<List<String>> lrange(String key, long start, long end);
	
	public RedisFuture<String> ltrim(String key, long start, long end);
	
	public RedisFuture<String> lindex(String key, long index);
	
	public RedisFuture<String> lset(String key, long index, String value);
	
	public RedisFuture<Long> lrem(String key, long count, String value);
	
	public RedisFuture<String> lpop(String key);
	
	public RedisFuture<String> rpop(String key);
	
	public RedisFuture<Long> sadd(String key, String... member);
	
	public RedisFuture<Set<String>> smembers(String key);
	
	public RedisFuture<Long> srem(String key, String... member);
	
	public RedisFuture<String> spop(String key);
	
	public RedisFuture<Long> scard(String key);
	
	public RedisFuture<Boolean> sismember(String key, String member);
	
	public RedisFuture<String> srandmember(String key);
	
	public RedisFuture<Long> strlen(String key);
	
	public RedisFuture<Long> zadd(String key, double score, String member);
	
	public RedisFuture<Long> zadd(String key, Map<Double, String> scoreMembers);
	
	public RedisFuture<Set<String>> zrange(String key, long start, long end);
	
	public RedisFuture<Long> zrem(String key, String... member);
	
	public RedisFuture<Double> zincrby(String key, double score, String member);
	
	public RedisFuture<Long> zrank(String key, String member);
	
	public RedisFuture<Long> zrevrank(String key, String member);
	
	public RedisFuture<Set<String>> zrevrange(String key, long start, long end);
	
	public RedisFuture<Set<Tuple>> zrangeWithScores(String key, long start, long end);
	
	public RedisFuture<Set<Tuple>> zrevrangeWithScores(String key, long start, long end);
	
	public RedisFuture<Long> zcard(String key);
	
	public RedisFuture<Double> zscore(String key, String member);
	
	public RedisFuture<List<String>> sort(String key);
	
	public RedisFuture<List<String>> sort(String key, SortingParams sortingParameters);
	
	public RedisFuture<Long> zcount(String key, double min, double max);
	
	public RedisFuture<Long> zcount(String key, String min, String max);
	
	public RedisFuture<Set<String>> zrangeByScore(String key, double min, double max);
	
	public RedisFuture<Set<String>> zrangeByScore(String key, String min, String max);
	
	public RedisFuture<Set<String>> zrevrangeByScore(String key, double max, double min);
	
	public RedisFuture<Set<String>> zrangeByScore(String key, double min, double max, int offset, int count);
	
	public RedisFuture<Set<String>> zrevrangeByScore(String key, String max, String min);
	
	public RedisFuture<Set<String>> zrangeByScore(String key, String min, String max, int offset, int count);
	
	public RedisFuture<Set<String>> zrevrangeByScore(String key, double max, double min, int offset, int count);
	
	public RedisFuture<Set<Tuple>> zrangeByScoreWithScores(String key, double min, double max);
	
	public RedisFuture<Set<Tuple>> zrevrangeByScoreWithScores(String key, double max, double min);
	
	public RedisFuture<Set<Tuple>> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);
	
	public RedisFuture<Set<String>> zrevrangeByScore(String key, String max, String min, int offset, int count);
	
	public RedisFuture<Set<Tuple>> zrangeByScoreWithScores(String key, String min, String max);
	
	public RedisFuture<Set<Tuple>> zrevrangeByScoreWithScores(String key, String max, String min);
	
	public RedisFuture<Set<Tuple>> zrangeByScoreWithScores(String key, String min, String max, int offset, int count);
	
	public RedisFuture<Set<Tuple>>
			zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);
	
	public RedisFuture<Set<Tuple>>
			zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count);
	
	public RedisFuture<Long> zremrangeByRank(String key, long start, long end);
	
	public RedisFuture<Long> zremrangeByScore(String key, double start, double end);
	
	public RedisFuture<Long> zremrangeByScore(String key, String start, String end);
	
	public RedisFuture<Long> linsert(String key, LIST_POSITION where, String pivot, String value);
	
	public RedisFuture<Long> lpushx(String key, String... string);
	
	public RedisFuture<Long> rpushx(String key, String... string);
	
	public RedisFuture<List<String>> blpop(String arg);
	
	public RedisFuture<List<String>> brpop(String arg);
	
	public RedisFuture<Long> del(String key);
	
	public RedisFuture<String> echo(String string);
	
	public RedisFuture<Long> move(String key, int dbIndex);
	
	public RedisFuture<Long> bitcount(String key);
	
	public RedisFuture<Long> bitcount(String key, long start, long end);
	
	// Multi-Key Commands
    public RedisFuture<Long> del(String... keys);

    public RedisFuture<List<String>> blpop(int timeout, String... keys);

    public RedisFuture<List<String>> brpop(int timeout, String... keys);

    public RedisFuture<List<String>> blpop(String... args);

    public RedisFuture<List<String>> brpop(String... args);

    public RedisFuture<Set<String>> keys(String pattern);

    public RedisFuture<List<String>> mget(String... keys);

    public RedisFuture<String> mset(String... keysvalues);

    public RedisFuture<Long> msetnx(String... keysvalues);

    public RedisFuture<String> rename(String oldkey, String newkey);

    public RedisFuture<Long> renamenx(String oldkey, String newkey);

    public RedisFuture<String> rpoplpush(String srckey, String dstkey);

    public RedisFuture<Set<String>> sdiff(String... keys);

    public RedisFuture<Long> sdiffstore(String dstkey, String... keys);

    public RedisFuture<Set<String>> sinter(String... keys);

    public RedisFuture<Long> sinterstore(String dstkey, String... keys);

    public RedisFuture<Long> smove(String srckey, String dstkey, String member);

    public RedisFuture<Long> sort(String key, SortingParams sortingParameters, String dstkey);

    public RedisFuture<Long> sort(String key, String dstkey);

    public RedisFuture<Set<String>> sunion(String... keys);

    public RedisFuture<Long> sunionstore(String dstkey, String... keys);

    public RedisFuture<String> watch(String... keys);

    public RedisFuture<String> unwatch();

    public RedisFuture<Long> zinterstore(String dstkey, String... sets);

    public RedisFuture<Long> zinterstore(String dstkey, ZParams params, String... sets);

    public RedisFuture<Long> zunionstore(String dstkey, String... sets);

    public RedisFuture<Long> zunionstore(String dstkey, ZParams params, String... sets);

    public RedisFuture<String> brpoplpush(String source, String destination, int timeout);

    public RedisFuture<Long> publish(String channel, String message);

    void subscribe(JedisPubSub jedisPubSub, String... channels);

    void psubscribe(JedisPubSub jedisPubSub, String... patterns);

    String randomKey();

    Long bitop(BitOP op, final String destKey, String... srcKeys);
	
	// Scripting Commands
	public RedisFuture<Object> eval(String script, int keyCount, String... params);
	
	public RedisFuture<Object> eval(String script, List<String> keys, List<String> args);
	
	public RedisFuture<Object> eval(String script);
	
	public RedisFuture<Object> evalsha(String script);
	
	public RedisFuture<Object> evalsha(String sha1, List<String> keys, List<String> args);
	
	public RedisFuture<Object> evalsha(String sha1, int keyCount, String... params);
	
	public RedisFuture<Boolean> scriptExists(String sha1);
	
	public RedisFuture<List<Boolean>> scriptExists(String... sha1);
	
	public RedisFuture<String> scriptLoad(String script);
	
	// Advanced Commands
    public RedisFuture<List<String>> configGet(String pattern);

    public RedisFuture<String> configSet(String parameter, String value);

    public RedisFuture<String> slowlogReset();

    public RedisFuture<Long> slowlogLen();

    public RedisFuture<List<Slowlog>> slowlogGet();

    public RedisFuture<List<Slowlog>> slowlogGet(long entries);

    public RedisFuture<Long> objectRefcount(String string);

    public RedisFuture<String> objectEncoding(String string);

    public RedisFuture<Long> objectIdletime(String string);
	
}
