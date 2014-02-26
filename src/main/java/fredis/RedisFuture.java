package fredis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * This class represents a deferred response to a Redis operation.
 * 
 * @author Joel Edwards &lt;joeledwards@gmail.com&gt;
 *
 * @param <V> the type of the value which may be returned by this {@link Future}.
 */
public class RedisFuture<V> implements Future<V>
{
	private CountDownLatch latch = new CountDownLatch(1);
	private Container<V> container = null;
	
	void setValue(V value)
	{
		synchronized(latch)
		{
			if (latch.getCount() < 1)
				return;
			
			latch.countDown();
			this.container = new Container<V>(value);
		}
	}
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning)
	{
		synchronized(latch)
		{
			if (latch.getCount() > 0 || !container.isEmpty())
				return false;
			
			container = new Container<V>();
			latch.countDown();
			return true;
		}
	}
	
	@Override
	public boolean isCancelled()
	{
		return (container != null) && container.isEmpty();
	}
	
	@Override
	public boolean isDone()
	{
		return (container != null) && !container.isEmpty();
	}
	
	@Override
	public V get() throws InterruptedException, ExecutionException
	{
		latch.await();
		return (container == null) ? null : container.getContent();
	}
	
	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
	{
		latch.await(timeout, unit);
		return (container == null) ? null : container.getContent();
	}
	
	/**
	 * This class represents an optionally empty container.
	 * 
	 * @author Joel Edwards &lt;joeledwards@gmail.com&gt;
	 *
	 * @param <T>
	 */
	private class Container<T>
	{
		private T content;
		
		public Container()
		{
		}
		
		public Container(T content)
		{
			if (content == null)
				throw new IllegalArgumentException("The parameter \"content\" must not be null.");
			
			this.content = content;
		}
		
		public boolean isEmpty()
		{
			return content == null;
		}
		
		public T getContent()
		{
			return content;
		}
	}
}
