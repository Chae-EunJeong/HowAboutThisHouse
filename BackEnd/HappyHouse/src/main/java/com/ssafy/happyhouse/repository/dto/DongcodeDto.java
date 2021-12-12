package com.ssafy.happyhouse.repository.dto;

public class DongcodeDto {

    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;

    public void setDongCode(String dongCode) { 
        this.dongCode = dongCode;
    }

    public String getDongCode() { 
        return dongCode;
    }

    public void setSidoName(String sidoName) { 
        this.sidoName = sidoName;
    }

    public String getSidoName() { 
        return sidoName;
    }

    public void setGugunName(String gugunName) { 
        this.gugunName = gugunName;
    }

    public String getGugunName() { 
        return gugunName;
    }

    public void setDongName(String dongName) { 
        this.dongName = dongName;
    }

    public String getDongName() { 
        return dongName;
    }
}