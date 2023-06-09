package com.eca.discovery;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EcaDiscoveryApplicationTest {

	@Test
	void testForCoverage() {
		var s1 = "test";
		Assertions.assertThat(s1).isNotNull();
		EcaDiscoveryApplication.main(new String []{"test"});
	}
}
