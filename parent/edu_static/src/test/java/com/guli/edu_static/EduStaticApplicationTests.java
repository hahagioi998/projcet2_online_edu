package com.guli.edu_static;

import com.guli.edu_static.service.StatisticsDailyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class EduStaticApplicationTests {



	@Autowired
	StatisticsDailyService statisticsDailyService;



	@Test
	void contextLoads() {

		Map<String, Object> register_num = statisticsDailyService.getQueryCondition("register_num", "2018-10-16", "2018-10-20");


	}


}
