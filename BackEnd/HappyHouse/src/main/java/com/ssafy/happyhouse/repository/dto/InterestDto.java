package com.ssafy.happyhouse.repository.dto;

public class InterestDto {
	private int interestNo;
    private String email;
    private String dongCode;
    private String interestSido;
    private String interestGugun;
    private String interestDong;
    
    public int getInterestNo() {
    	return interestNo;
    }
    
    public void setInterestNo(int interestNo) {
    	this.interestNo = interestNo;
    }

    public void setEmail(String email) { 
        this.email = email;
    }

    public String getEmail() { 
        return email;
    }
    
    public String getDongCode() {
    	return dongCode;
    }
    
    public void setDongCode(String dongCode) {
    	this.dongCode = dongCode;
    }

    public void setInterestSido(String interestSido) { 
        this.interestSido = interestSido;
    }

    public String getInterestSido() { 
        return interestSido;
    }

    public void setInterestGugun(String interestGugun) { 
        this.interestGugun = interestGugun;
    }

    public String getInterestGugun() { 
        return interestGugun;
    }

    public void setInterestDong(String interestDong) { 
        this.interestDong = interestDong;
    }

    public String getInterestDong() { 
        return interestDong;
    }
}