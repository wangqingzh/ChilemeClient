package com.wangqing.chilemecilent.object.ao;

import java.util.List;

public class CheckToken {

    /**
     * aud : ["rid"]
     * user_name : admin
     * scope : ["all"]
     * active : true
     * exp : 1581581389
     * authorities : ["ROLE_user"]
     * client_id : user
     */

    private String user_name;
    private boolean active;
    private int exp;
    private String client_id;
    private List<String> aud;
    private List<String> scope;
    private List<String> authorities;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public List<String> getAud() {
        return aud;
    }

    public void setAud(List<String> aud) {
        this.aud = aud;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
