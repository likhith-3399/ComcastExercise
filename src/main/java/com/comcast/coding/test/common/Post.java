package com.comcast.coding.test.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

	private Integer userId;
	private Integer id;
    private String title;
    private String body;
   
    public Post() {
    
    }

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/*@Override
    public String toString() {
		return new StringBuilder()
				.append("User Id : ").append(getUserId())
				.append("Id : ").append(getId())
				.append("Title : ").append(getTitle())
				.append("Text String : ").append(getBody())				
				.toString();
		
	}*/
}