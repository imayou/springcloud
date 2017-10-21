package com.ayou.spring;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeAction {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Registration registration;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {

		List<ServiceInstance> instance = client.getInstances(registration.getServiceId());
		Integer r = a + b;
		instance.forEach((ins) -> {
			logger.info("/add, host:" + ins.getHost() + ", service_id:" + ins.getServiceId() + ", result:" + r);
		});

		return r;
	}
}
