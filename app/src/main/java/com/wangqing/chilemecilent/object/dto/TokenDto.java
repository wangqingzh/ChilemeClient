package com.wangqing.chilemecilent.object.dto;

/**
 * 获取 token 成功返回结果
 */
public class TokenDto {

    /**
     * access_token : 856f8c56-e149-4328-ae66-73351e2b8a7a
     * token_type : bearer
     * refresh_token : 535903a0-29e2-4c06-abca-9ef2e78520be
     * expires_in : 85131
     * scope : all
     */
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
