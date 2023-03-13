package com.my.github.majiang.community.mytestcommunity.model;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

//import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author  arrogantKatze 
 * @Date 2023-03-13 22:16:17 
 */

@Data
@Accessors(chain = true)
//@Entity
//@Table(name = "QUESTION")
public class Question implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID")
	private Integer id;

//	@Column(name = "TITLE")
	private String title;

//	@Column(name = "DESCRIPTION")
	private String description;

//	@Column(name = "CREATE_TIME")
	private java.sql.Timestamp createTime;

//	@Column(name = "UPDATE_TIME")
	private java.sql.Timestamp updateTime;

//	@Column(name = "CREATOR")
	private Integer creator;

//	@Column(name = "COMMENT_COUNT")
	private Integer commentCount;

//	@Column(name = "VIEW_COUNT")
	private Integer viewCount;

//	@Column(name = "TAG")
	private String tag;

}
