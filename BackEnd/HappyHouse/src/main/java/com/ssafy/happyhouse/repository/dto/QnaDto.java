package com.ssafy.happyhouse.repository.dto;

import java.util.Date;
public class QnaDto {

    private int qnaNo;
    private String email;
    private String name;
    private String qnaTitle;
    private String qnaContent;
    private int viewCnt;
    private int originNo;
    private int groupOrd;
    private String regDate;

    public void setQnaNo(int qnaNo) { 
        this.qnaNo = qnaNo;
    }

    public int getQnaNo() { 
        return qnaNo;
    }

    public void setEmail(String email) { 
        this.email = email;
    }

    public String getEmail() { 
        return email;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public String getName() { 
        return name;
    }

    public void setQnaTitle(String qnaTitle) { 
        this.qnaTitle = qnaTitle;
    }

    public String getQnaTitle() { 
        return qnaTitle;
    }

    public void setQnaContent(String qnaContent) { 
        this.qnaContent = qnaContent;
    }

    public String getQnaContent() { 
        return qnaContent;
    }

    public void setViewCnt(int viewCnt) { 
        this.viewCnt = viewCnt;
    }

    public int getViewCnt() { 
        return viewCnt;
    }

    public int getOriginNo() {
    	return originNo;
    }
    
    public void setOriginNo(int originNo) {
    	this.originNo = originNo;
    }
    
    public int getGroupOrd() {
    	return groupOrd;
    }
    
    public void setGroupOrd(int groupOrd) {
    	this.groupOrd = groupOrd;
    }

    public void setRegDate(String regDate) { 
        this.regDate = regDate;
    }

    public String getRegDate() { 
        return regDate;
    }

}