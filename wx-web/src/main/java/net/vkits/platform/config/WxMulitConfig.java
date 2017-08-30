package net.vkits.platform.config;

/**
 * Created by duqingxiang on 17/7/3.
 */
public class WxMulitConfig extends WxConfig {

    private String clientId;

    private String token;

    private String appid;

    private String appsecret;

    private String aesKey;

    private String templateId;

    private String billTemplateId;

    public void setToken(String token) {
        this.token = token;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String getAppid() {
        return this.appid;
    }

    @Override
    public String getAppsecret() {
        return this.appsecret;
    }

    @Override
    public String getAesKey() {
        return this.aesKey;
    }

    @Override
    public WxAccountEnum getWxAccountEnum() {
        return null;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getBillTemplateId() {
        return billTemplateId;
    }

    public void setBillTemplateId(String billTemplateId) {
        this.billTemplateId = billTemplateId;
    }
}
