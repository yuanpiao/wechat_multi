package net.vkits.platform.handler.gzh1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.vkits.platform.config.WxConfig;
import net.vkits.platform.config.WxGzh1Config;
import net.vkits.platform.handler.SubscribeHandler;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class Gzh1SubscribeHandler extends SubscribeHandler {
  @Autowired
  private  WxGzh1Config wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

  @Override
  protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) {
    return null;
  }

}
