package com.ssafy.happyhouse.repository.dto;

import java.util.Date;
public class CommentDto {

    private int commentNo;
    private String email;
    private String name;
    private String content;
    private String regDate;
    private int boardNo;

    public void setCommentNo(int commentNo) { 
        this.commentNo = commentNo;
    }

    public int getCommentNo() { 
        return commentNo;
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

    public void setContent(String content) { 
        this.content = content;
    }

    public String getContent() { 
        return content;
    }

    public void setRegDate(String regDate) { 
        this.regDate = regDate;
    }

    public String getRegDate() { 
        return regDate;
    }

    public void setBoardNo(int boardNo) { 
        this.boardNo = boardNo;
    }

    public int getBoardNo() { 
        return boardNo;
    }
}