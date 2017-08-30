package net.vkits.platform.service;

import net.vkits.platform.dto.WxMulitConfigDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chi  2017-08-29 19:22
 **/
@Service
public class WxMulitConfigService {

    @Resource
    private WxMulitConfigWebService wxMulitConfigWebService;

    public List<WxMulitConfigDto> getAllWxConfig(){
        return wxMulitConfigWebService.getAllWxConfig();
    }
}
