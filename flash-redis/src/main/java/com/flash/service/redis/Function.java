package com.flash.service.redis;
/**
 * 用于redis
 * @author leon
 *
 * @param <E>
 * @param <T>
 */
public interface Function<E, T> {
	public T execute(E e);
}
