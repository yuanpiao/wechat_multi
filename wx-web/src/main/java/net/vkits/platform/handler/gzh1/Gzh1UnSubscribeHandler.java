package net.vkits.platform.handler.gzh1;

import net.vkits.platform.handler.UnsubscribeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.vkits.platform.config.WxConfig;
import net.vkits.platform.config.WxGzh1Config;

@Component
public class Gzh1UnSubscribeHandler extends UnsubscribeHandler {
  @Autowired
  private  WxGzh1Config wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
