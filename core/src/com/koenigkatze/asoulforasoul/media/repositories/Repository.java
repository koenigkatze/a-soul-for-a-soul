package com.koenigkatze.asoulforasoul.media.repositories;

public interface Repository<V>
{
	public void put(RepositoryKey key, V value);

	public V get(RepositoryKey key);

	public String getName();

}
