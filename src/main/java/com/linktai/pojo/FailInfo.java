package com.linktai.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FailInfo {
    private Integer id;

    private String name;

    private String country;

    private String company;

    private String position;

    private String email;

    private String telephine;

    private String cardnumber4;

    private String failmessage;

    private Date failtime;
    
    private String failure_code;
    
    private String dateFormat;
    
    private Integer failnum;
    
    public FailInfo() {
		// TODO Auto-generated constructor stub
	}

    public FailInfo(Integer id, String name, String country, String company, String position, String email,
			String telephine, String cardnumber4, String failmessage, Date failtime,String failure_code) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.company = company;
		this.position = position;
		this.email = email;
		this.telephine = telephine;
		this.cardnumber4 = cardnumber4;
		this.failmessage = failmessage;
		this.failtime = failtime;
		this.failure_code=failure_code;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephine() {
        return telephine;
    }

    public void setTelephine(String telephine) {
        this.telephine = telephine;
    }

    public String getCardnumber4() {
        return cardnumber4;
    }

    public void setCardnumber4(String cardnumber4) {
        this.cardnumber4 = cardnumber4;
    }

    public String getFailmessage() {
        return failmessage;
    }

    public void setFailmessage(String failmessage) {
        this.failmessage = failmessage;
    }

    public Date getFailtime() {
        return failtime;
    }

    public void setFailtime(Date failtime) {
        this.failtime = failtime;
    }

	public String getFailure_code() {
		return failure_code;
	}

	public void setFailure_code(String failure_code) {
		this.failure_code = failure_code;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.dateFormat = format.format(failtime);
	}

	public Integer getFailnum() {
		return failnum;
	}

	public void setFailnum(Integer failnum) {
		this.failnum = failnum;
	}
	
	
	
	
    
}