package com.linktai.pojo;

public class Managers {
    private Integer id;

    private String loginname;

    private String loginpsw;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpsw() {
        return loginpsw;
    }

    public void setLoginpsw(String loginpsw) {
        this.loginpsw = loginpsw;
    }

	@Override
	public String toString() {
		return "Managers [id=" + id + ", loginname=" + loginname + ", loginpsw=" + loginpsw + "]";
	}
    
}