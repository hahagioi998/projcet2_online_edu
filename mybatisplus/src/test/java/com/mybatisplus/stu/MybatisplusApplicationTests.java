package com.mybatisplus.stu;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.stu.bean.User;
import com.mybatisplus.stu.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;



@SpringBootTest
class MybatisplusApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void t1(){

		List<User> users = userMapper.selectList(null);
		System.err.println(users);
	}

	    @Test
	        public void Test2(){

			User zs = new User( "locker", 999, "123321@qq.com");

			int index = userMapper.insert(zs);
			System.err.println(index);


		}


		    @Test
		        public void TestPage(){

				Page<User> page = new Page<>(1, 3);
				userMapper.selectPage(page, null);

				System.out.println(page.getCurrent());
				System.out.println(page.getRecords());


			}


			    @Test
			        public void Test(){

					HashMap<String, Object> map = new HashMap<>();
					map.put("name" , "Jone");

					userMapper.selectByMap(map).forEach(System.out::println);


				}

				    @Test
				        public void Test3(){

						QueryWrapper<User> wap = new QueryWrapper<>();


						wap.eq("name" , "Jone");


						wap.between("age" ,30 , 200);

						wap.likeLeft("name" , "a");


						userMapper.selectList(wap).forEach(System.out::println);





					}

					    @Test
					        public void Test5(){

							String a = "" ;
							String b = null;

							System.err.println("a的结果是：  " + StringUtils.isEmpty(a));
							System.err.println("b的结果是：  " + StringUtils.isEmpty(b));


					        }




}
