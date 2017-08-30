package net.vkits.platform.handler.customer;


import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import net.vkits.platform.application.listener.event.RepayMessageEvent;
import net.vkits.platform.application.wechat.builder.TextBuilder;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.config.WxCustomerConfig;
import net.vkits.platform.application.wechat.handler.MsgHandler;
import net.vkits.platform.application.wechat.service.BaseWxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class CustomerMsgHandler extends MsgHandler {
  @Autowired
  private WxCustomerConfig wxConfig;

    @Resource
    private ApplicationContext applicationContext;


  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager)    {


      try {
          if (wxMessage.getContent().indexOf("还款") > -1) {
              applicationContext.publishEvent(new RepayMessageEvent(wxMessage.getFromUser()));
              BaseWxService weixinService = (BaseWxService) wxMpService;
              return new TextBuilder().build("请点击 “我的账单”,进入后点击下方 “立即还款” 进行支付。",wxMessage,weixinService);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }


      BaseWxService weixinService = (BaseWxService) wxMpService;

      if (!wxMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)) {
        //TODO 可以选择将消息保存到本地
      }

      //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
      if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
              && weixinService.hasKefuOnline()) {
        return WxMpXmlOutMessage
                .TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser()).build();
      }

      //TODO 组装回复消息
      String content = "回复信息内容";
  //        return new TextBuilder().build(content, wxMessage, weixinService);
      //暂时要求不回复任何信息
      return null;

  }

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
