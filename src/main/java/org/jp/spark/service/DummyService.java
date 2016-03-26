package org.jp.spark.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jp.spark.model.Message;
import org.springframework.stereotype.Service;


@Service
public class DummyService {

	public List<Message> getDummyMessages() {
		return Arrays.asList("dummy message 1","dummy message 2","dummy message 3")
			.stream()
			.map(message -> new Message(message))
			.collect(Collectors.toList());
	}
}
