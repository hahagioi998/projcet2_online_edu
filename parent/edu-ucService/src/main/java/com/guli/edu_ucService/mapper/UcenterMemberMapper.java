package com.guli.edu_ucService.mapper;

import com.guli.edu_ucService.bean.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countRegistNum(String day);
}
