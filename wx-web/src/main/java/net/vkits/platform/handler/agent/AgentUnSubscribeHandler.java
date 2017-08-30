package net.vkits.platform.handler.agent;


import net.vkits.platform.application.wechat.config.WxAgentConfig;
import net.vkits.platform.application.wechat.config.WxConfig;
import net.vkits.platform.application.wechat.handler.UnsubscribeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentUnSubscribeHandler extends UnsubscribeHandler {
  @Autowired
  private WxAgentConfig wxConfig;

  @Override
  protected WxConfig getWxConfig() {
    return this.wxConfig;
  }

}
