package com.qf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.pojo.UserAddr;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户地址  Mapper 接口
 * </p>
 *
 * @author 威哥
 * @since 2022-08-23
 */
public interface UserAddrMapper extends BaseMapper<UserAddr> {
    int changeAddressById(@Param("id") int addressId,@Param("status") int statusNum);
}
