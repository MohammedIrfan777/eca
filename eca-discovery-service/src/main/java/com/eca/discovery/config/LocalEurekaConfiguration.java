package com.eca.discovery.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

@Configuration
@ConditionalOnExpression("${eureka.docker.mode:false}")
@Slf4j
public class LocalEurekaConfiguration {

	@Value("${server.port}")
	private Integer localEurekaport;

	/*@Bean
	@Primary
	public EurekaInstanceConfigBean eurekaInstanceConfig() {
		InetUtilsProperties inetUtilsProperties = new InetUtilsProperties();
		inetUtilsProperties.setDefaultHostname(localEurekaHost);
		inetUtilsProperties.setDefaultIpAddress(localEurekaHost);

		InetUtils inetUtils = new InetUtils(inetUtilsProperties);
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);

		config.setHostname(localEurekaHost);
		config.setIpAddress(localEurekaHost);
		config.setNonSecurePort(localEurekaport);
		return config;
	}

	@Bean
	@Primary
	public DiscoveryClient discoveryClient(EurekaClient client) {
		return new EurekaDiscoveryClient(eurekaInstanceConfig(), client);
	}*/

	@Bean
	@Autowired
	public EurekaInstanceConfigBean eurekaInstanceConfig(final InetUtils inetUtils) throws IOException {
		final String hostName = System.getenv("EUREKA_HOST_NAME");
		log.info("### HOSTNAME {} ",hostName);
		String hostAddress = null;
		final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		log.info("### eurekaInstanceConfig {} ",networkInterfaces);
		for (NetworkInterface netInt : Collections.list(networkInterfaces)) {
			for (InetAddress inetAddress : Collections.list(netInt.getInetAddresses())) {
				if (hostName.equals(inetAddress.getHostName())) {
					hostAddress = inetAddress.getHostAddress();
				}
			}
		}
		log.info("### hostAddress {} ",hostAddress);
		final EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		config.setHostname(hostName);
		config.setIpAddress(hostAddress);
		return config;
	}
}
