package com.guli.edu_ucService.service;

import com.guli.edu_ucService.bean.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    Integer countRegistNum(String day);
}
