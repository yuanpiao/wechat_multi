package net.vkits.platform.wxService;

import net.vkits.platform.config.WxMulitConfig;
import net.vkits.platform.dto.WxMulitConfigDto;
import net.vkits.platform.service.WxMulitConfigWebService;
import net.vkits.platform.service.WxMulitConfigWebServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chi
 * @Date: 12:34 2017/10/11/011
 */

@Service
public class MulitConfigService {

    @Resource
    private WxMulitConfigWebService wxMulitConfigWebService;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 缓存商户id和对应的wxmulitService,如果集群条件下可以使用redis等缓存
     */
    private ConcurrentHashMap<String, WxMulitService> wxMulitServiceMaps = new ConcurrentHashMap<>();

    /**
     * @discription: 根据不同的clientId获得不同的wxservice
     * @author: chi
     * @Date: 18:59 2017/8/29/029
     */
    public WxMulitService getWxMulitService(String clientId){
        return wxMulitServiceMaps.get(clientId);
    }


    /**
     * @description: 项目启动时缓存多个商户的wxService
     * @author: chi
     * @Date: 12:32 2017/10/11/011
     */
    @PostConstruct
    public void wxMulitServiceInit(){
        List<WxMulitConfigDto> wxMulitConfigDtos = wxMulitConfigWebService.getAllWxConfig();
        wxMulitConfigDtos.forEach(this::generateWxMulitConfig);
    }

    public void generateWxMulitConfig(WxMulitConfigDto wxMulitConfigDto){
        //wxMulitService设置为多例,保证每一个商户有各自的wxService
        WxMulitService wxMulit = (WxMulitService)applicationContext.getBean("wxMulitService");
        WxMulitConfig wxConfig = WxMulitConfig.builder()
                .clientId(wxMulitConfigDto.getClientId())
                .aesKey(wxMulitConfigDto.getAesKey())
                .appid(wxMulitConfigDto.getAppid())
                .appsecret(wxMulitConfigDto.getAppsecret())
                .token(wxMulitConfigDto.getToken())
                .billTemplateId(wxMulitConfigDto.getBillTemplateId())
                .templateId(wxMulitConfigDto.getTemplateId())
                .build();

        wxMulit.setServerConfig(wxConfig).init();
        wxMulitServiceMaps.put(wxMulitConfigDto.getClientId(),wxMulit);
    }



}
