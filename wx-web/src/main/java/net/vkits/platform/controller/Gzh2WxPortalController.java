package net.vkits.platform.controller;

import net.vkits.platform.wxService.BaseWxService;
import net.vkits.platform.wxService.Gzh2WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第二个公众号的微信交互接口
 * @author Binary Wang
 *
 */
@RestController
@RequestMapping("/api/gzh2/portal")
public class Gzh2WxPortalController extends AbstractWxPortalController{
  @Autowired
  private Gzh2WxService wxService;
  
  @Override
  protected BaseWxService getWxService() {
    return this.wxService;
  }

}
