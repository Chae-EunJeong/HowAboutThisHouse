package com.ssafy.happyhouse.repository.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "Notice : 공지사항 정보", description = "공지사항의 상세 정보를 나타낸다.")
public class NoticeDto {
	@ApiModelProperty(value = "공지사항 번호")
    private int noticeNo;
	@ApiModelProperty(value = "공지사항 제목")
    private String noticeTitle;
	@ApiModelProperty(value = "작성일")
    private String regDate;
	@ApiModelProperty(value = "작성자 이메일")
    private String email;
	@ApiModelProperty(value = "작성자 이름")
    private String name;
	@ApiModelProperty(value = "조회수")
    private int viewCnt;
	@ApiModelProperty(value = "공지사항 내용")
    private String noticeContent;

    public void setNoticeNo(int noticeNo) { 
        this.noticeNo = noticeNo;
    }

    public int getNoticeNo() { 
        return noticeNo;
    }

    public void setNoticeTitle(String noticeTitle) { 
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle() { 
        return noticeTitle;
    }

    public void setRegDate(String regDate) { 
        this.regDate = regDate;
    }

    public String getRegDate() { 
        return regDate;
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

    public void setViewCnt(int viewCnt) { 
        this.viewCnt = viewCnt;
    }

    public int getViewCnt() { 
        return viewCnt;
    }

    public void setNoticeContent(String noticeContent) { 
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent() { 
        return noticeContent;
    }
}