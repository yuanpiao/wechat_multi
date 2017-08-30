package net.vkits.platform.handler.customer;

import com.alibaba.fastjson.JSON;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.vkits.platform.application.counter.CustomerMessageService;
import net.vkits.platform.application.wechat.builder.AbstractBuilder;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.config.WxCustomerConfig;
import net.vkits.platform.application.wechat.dto.WxMenuKey;
import net.vkits.platform.application.wechat.handler.MenuHandler;
import net.vkits.platform.application.wechat.service.BaseWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerMenuHandler extends MenuHandler {
    @Autowired
    private WxCustomerConfig wxConfig;

    @Autowired
    CustomerMessageService customerMessageService;

    @Override
    protected WxConfig getWxConfig() {
    return this.wxConfig;
    }

    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        BaseWxService weixinService = (BaseWxService) wxMpService;

        String key = wxMessage.getEventKey();
        WxMenuKey menuKey = null;
        try {
            menuKey = JSON.parseObject(key, WxMenuKey.class);
        } catch (Exception e) {
            return WxMpXmlOutMessage.TEXT().content(key)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
        }

        AbstractBuilder builder = super.getBuilder(menuKey);

        if (builder != null) {
            try {
                return builder.build(customerMessageService.getMenuBtnMessage(wxMessage,menuKey.getContent()), wxMessage, weixinService);
            } catch (Exception e) {
                this.logger.error(e.getMessage(), e);
            }
        }

        return null;

    }

}
