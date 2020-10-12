package com.guli.edu_static.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.Result;
import com.guli.edu_static.bean.StatisticsDaily;
import com.guli.edu_static.mapper.StatisticsDailyMapper;
import com.guli.edu_static.service.StatisticsDailyService;
import com.guli.edu_static.service.UserCenterFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {


    @Autowired
    UserCenterFeign userCenterFeign;



    @Override
    public Result countRegistNum(String day) {




        System.err.println("day =======" + day);

        Result result = userCenterFeign.countRegistNum(day);

        Integer num =(Integer) result.getData().get("items");

//        Integer num = 1;

        StatisticsDaily daily = new StatisticsDaily();
        daily.setCourseNum(num);
        daily.setRegisterNum(num);
        daily.setDateCalculated(day);
        daily.setLoginNum(num);
        daily.setVideoViewNum(num);

        baseMapper.insert(daily);
        System.err.println(daily);


        return Result.ok().message("查询成功");
    }

    @Override
    public Map<String, Object> getQueryCondition(String type, String begin, String end) {


        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated" , begin , end);
        wrapper.select("date_calculated" , type);
        List<StatisticsDaily> list = baseMapper.selectList(wrapper);

        System.err.println("getQueryCondition  list :  " + list);

//        new HashMap<>()
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> msg = new ArrayList<>();

        for (StatisticsDaily daily : list) {
            date.add(daily.getDateCalculated());

            switch (type){

                case "register_num":
                    msg.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    msg.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    msg.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    msg.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }



        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("time" , date);
        map.put("data" , msg);

        System.err.println(" getQueryCondition  return map: " + map);


        return map;
    }
}
