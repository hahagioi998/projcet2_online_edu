package com.guli.edu_static.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.common.Result;
import com.guli.edu_static.bean.StatisticsDaily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    Result countRegistNum(String day);

    Map<String, Object> getQueryCondition(String type, String begin, String end);
}
