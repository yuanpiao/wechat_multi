package net.vkits.platform.handler.mulit;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.vkits.platform.application.listener.event.RepayMessageEvent;
import net.vkits.platform.application.wechat.builder.TextBuilder;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.handler.MsgHandler;
import net.vkits.platform.application.wechat.service.BaseWxService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class MulitMsgHandler extends MsgHandler {

  private WxConfig wxConfig;

  @Resource
  private ApplicationContext applicationContext;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

  public void setWxConfig(WxConfig wxConfig) {
    this.wxConfig = wxConfig;
  }

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {

    try {
        if (wxMessage.getContent().indexOf("还款") > -1) {
          applicationContext.publishEvent(new RepayMessageEvent(wxMessage.getFromUser()));
          BaseWxService weixinService = (BaseWxService) wxMpService;
          return new TextBuilder().build("请点击 “我的账单”,进入后点击下方 “立即还款” 进行支付。",wxMessage,weixinService);
        }
    } catch (Exception e) {
       e.printStackTrace();
    }


    return null;
  }
}
