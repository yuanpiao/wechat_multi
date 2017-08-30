package net.vkits.platform.handler.customer;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import net.vkits.platform.application.counter.CustomerMessageService;
import net.vkits.platform.application.counter.dto.enums.UserTagsEnums;
import net.vkits.platform.application.listener.event.RegisterEvent;
import net.vkits.platform.application.wechat.builder.TextBuilder;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.config.WxCustomerConfig;
import net.vkits.platform.application.wechat.handler.SubscribeHandler;
import net.vkits.platform.application.wechat.service.BaseWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class CustomerSubscribeHandler extends SubscribeHandler {
  @Autowired
  private WxCustomerConfig wxConfig;

  @Autowired
  CustomerMessageService customerMessageService;

  @Resource
  private ApplicationContext applicationContext;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

      this.logger.info("customer 新关注用户 OPENID: " + wxMessage.getFromUser());

      BaseWxService weixinService = (BaseWxService) wxMpService;

      // 获取微信用户基本信息
      WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

      if (userWxInfo != null) {
          // 注册入库
          RegisterEvent event = RegisterEvent.builder()
                  .userInfo(userWxInfo)
                  .tag(UserTagsEnums.CUSTOMER)
                  .build();
          applicationContext.publishEvent(event);
      }

      return this.getResponseMessage(wxMessage);
  }

  @Override
  protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) {

      if (StringUtils.isEmpty(wxMessage.getEventKey()))
        return null;

      String param = wxMessage.getEventKey().replace("qrscene_","");


      return new TextBuilder().build(customerMessageService.getScanQRCodeMessage(wxMessage.getFromUser(),Integer.valueOf(param)), wxMessage, null);
  }

  @Override
  protected WxMpXmlOutMessage handleNormal(WxMpXmlMessage wxMessage) throws Exception {
      return new TextBuilder().build(customerMessageService.getNormalMessage(wxMessage), wxMessage, null);
  }

}