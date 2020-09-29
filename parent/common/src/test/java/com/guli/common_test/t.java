package com.guli.common_test;

import com.guli.common.Result;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class t {






        @Test
            public void Test(){


            Result ok = new Result().ok();

            Map<String, Object> data = ok.getData();
            data.put("a" , Arrays.asList(1,2,3,4));

            System.err.println(ok);


        }






}
