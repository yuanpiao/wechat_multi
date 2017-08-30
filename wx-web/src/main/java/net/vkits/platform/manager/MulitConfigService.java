package net.vkits.platform.manager;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import net.vkits.platform.config.WxConfig;
import net.vkits.platform.config.WxMulitConfig;
import net.vkits.platform.dto.WxMulitConfigDto;
import net.vkits.platform.service.WxMulitConfigWebService;
import net.vkits.platform.util.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class MulitConfigService {

    @Resource
    private WxMulitConfigWebService wxMulitConfigWebService;

    @Resource
    private WxMulitService wxMulitService;

    @Resource
    private SpringUtil springUtil;

    private ConcurrentHashMap<String, WxMulitService> hashMap = new ConcurrentHashMap<>();

    /**
     * @discription: TODO chi 待实现
     * @author: chi
     * @Date: 18:59 2017/8/29/029
     */
    public WxMulitService getWxMulitService(String clientId){

        return hashMap.get(clientId);
    }

    @PostConstruct
    public void wxMulitServiceInit(){
        List<WxMulitConfigDto> wxMulitConfigDtos = wxMulitConfigWebService.getAllWxConfig();



      /*  wxMulitConfigDtos.stream()
                .peek(wxMulitConfigDto ->
                        hashMap.put(wxMulitConfigDto.getClientId(),new WxMulitService(wxMulitConfigDto)));
    */

        for (WxMulitConfigDto wxMulitConfigDto: wxMulitConfigDtos) {


            WxMulitService wxMulit = (WxMulitService)SpringUtil.getBean("wxMulitService");
            WxMulitConfig wxConfig = new WxMulitConfig();
            wxConfig.setClientId(wxMulitConfigDto.getClientId());
            wxConfig.setAesKey(wxMulitConfigDto.getAesKey());
            wxConfig.setAppid(wxMulitConfigDto.getAppid());
            wxConfig.setAppsecret(wxMulitConfigDto.getAppsecret());
            wxConfig.setBillTemplateId(wxMulitConfigDto.getBillTemplateId());
            wxConfig.setToken(wxMulitConfigDto.getToken());
            wxMulit.setServerConfig(wxConfig);
            wxMulit.init();

/*

            final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
            config.setAppId(wxMulitConfigDto.getAppid());// 设置微信公众号的appid
            config.setSecret(wxMulitConfigDto.getAppsecret());// 设置微信公众号的app corpSecret
            config.setToken(wxMulitConfigDto.getToken());// 设置微信公众号的token
            config.setAesKey(wxMulitConfigDto.getAesKey());// 设置消息加解密密钥
            //config.setApacheHttpClientBuilder(wxHttpClientBuilder);
            wxMulit.setWxMpConfigStorage(config);


*/

            hashMap.put(wxMulitConfigDto.getClientId(),wxMulit);
        }



    }


}
