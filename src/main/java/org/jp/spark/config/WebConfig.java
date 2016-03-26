package org.jp.spark.config;

import static org.jp.spark.config.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.SparkBase.staticFileLocation;

import org.jp.spark.model.HomeAssistant;
import org.jp.spark.service.DummyService;
import org.jp.spark.service.HomeAssistantService;

import com.google.gson.Gson;

public class WebConfig {

	private static final String HOME_ASSISTANT_PATH = "/home-assistant";
	private DummyService dummyService;
	private HomeAssistantService homeAssistantService;

	public WebConfig(DummyService service,
			HomeAssistantService homeAssistantService) {
		this.dummyService = service;
		this.homeAssistantService = homeAssistantService;
		port(getHerokuAssignedPort());
		staticFileLocation("/public");
		setupRoutes();
	}

	private void setupRoutes() {
		get("/messages", (req, res) -> dummyService.getDummyMessages(), json());

		get(HOME_ASSISTANT_PATH, (req, res) -> homeAssistantService.getAll(),
				json());

		post(HOME_ASSISTANT_PATH,
				(req, res) -> {
					HomeAssistant ha = new Gson().fromJson(req.body(),
							HomeAssistant.class);
					homeAssistantService.save(ha);
					return ha;
				}, json());
	}
	
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
