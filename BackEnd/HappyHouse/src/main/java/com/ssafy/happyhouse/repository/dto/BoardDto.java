package com.ssafy.happyhouse.repository.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDto : 게시글 정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {
	@ApiModelProperty(value = "글번호")
    private int boardNo;
	@ApiModelProperty(value = "작성자 이메일")
    private String email;
	@ApiModelProperty(value = "작성자 이름")
    private String name;
	@ApiModelProperty(value = "글제목")
    private String boardTitle;
	@ApiModelProperty(value = "글내용")
    private String boardContent;
	@ApiModelProperty(value = "조회수")
    private int viewCnt;
	@ApiModelProperty(value = "작성일")
    private String regDate;

    public void setBoardNo(int boardNo) { 
        this.boardNo = boardNo;
    }

    public int getBoardNo() { 
        return boardNo;
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

    public void setBoardTitle(String boardTitle) { 
        this.boardTitle = boardTitle;
    }

    public String getBoardTitle() { 
        return boardTitle;
    }

    public void setBoardContent(String boardContent) { 
        this.boardContent = boardContent;
    }

    public String getBoardContent() { 
        return boardContent;
    }

    public void setViewCnt(int viewCnt) { 
        this.viewCnt = viewCnt;
    }

    public int getViewCnt() { 
        return viewCnt;
    }

    public void setRegDate(String regDate) { 
        this.regDate = regDate;
    }

    public String getRegDate() { 
        return regDate;
    }
}