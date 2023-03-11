package com.my.github.majiang.community.mytestcommunity.model;

//import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author  arrogantKatze 
 * @Date 2023-03-11 22:45:03 
 */

@Data
@Accessors(chain = true)
//@Entity
//@Table(name = "USER")
public class User implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID")
	private Integer id;

//	@Column(name = "ACCOUNT_ID")
	private String accountId;

//	@Column(name = "NAME")
	private String name;

//	@Column(name = "TOKEN")
	private String token;

//	@Column(name = "CREATE_TIME")
	private java.sql.Timestamp createTime;

//	@Column(name = "UPDATE_TIME")
	private java.sql.Timestamp updateTime;

}
