package fredis;

public interface IRedisOperation<T>
{
	public RedisFuture<T> execute(IRedis redis);
}
