package org.jp.spark.dao;

import java.util.List;

import org.jp.spark.model.HomeAssistant;

public interface HomeAssistantDao {
	public List<HomeAssistant> getAll();
	public void save(HomeAssistant homeAssistant);
}
