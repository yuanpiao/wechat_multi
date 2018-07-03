package net.vkits.platform.service;

import net.vkits.platform.dto.WxMulitConfigDto;
import java.util.List;

public interface WxMulitConfigWebService {
    /**
     * 获得所有的多商户列表
     * @return
     */
    List<WxMulitConfigDto> getAllWxConfig();
}
