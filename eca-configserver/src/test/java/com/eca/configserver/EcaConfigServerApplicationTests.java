package com.eca.configserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EcaConfigServerApplicationTests {

	@Test
	void contextLoads() {
		var str = "1";
		Assertions.assertThat(str).isNotNull();
	}

}
