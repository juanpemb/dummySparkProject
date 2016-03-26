package org.jp.spark.service;

import java.util.List;

import org.jp.spark.dao.HomeAssistantDao;
import org.jp.spark.model.HomeAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeAssistantService {
	
	@Autowired
	private HomeAssistantDao homeAssistantDao;
	
	public List<HomeAssistant> getAll() {
		return homeAssistantDao.getAll();
	}

	public HomeAssistant save(HomeAssistant homeAssistant) {
		homeAssistantDao.save(homeAssistant);
		return homeAssistant;
	}
}
