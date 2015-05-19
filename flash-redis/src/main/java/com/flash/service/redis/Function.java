package com.flash.service.redis;

public interface Function<E, T> {
	public T execute(E e);
	
}
