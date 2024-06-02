package com.car.rental.justride;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.justride.JustrideApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JustrideApplication.class)
public class SpringContextTest {

	@Test
	public void whenSpringContextIsBootstrapped_thenNoExceptions() {
	}
}
