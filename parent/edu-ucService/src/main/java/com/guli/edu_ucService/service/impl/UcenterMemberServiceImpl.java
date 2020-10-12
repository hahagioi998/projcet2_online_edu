package com.guli.edu_ucService.service.impl;

import com.guli.edu_ucService.bean.UcenterMember;
import com.guli.edu_ucService.mapper.UcenterMemberMapper;
import com.guli.edu_ucService.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public Integer countRegistNum(String day) {


        return baseMapper.countRegistNum(day);
    }
}
