package com.example.springboot.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@TableName("`user`")
@Accessors(chain=true)
@NoArgsConstructor
@ToString
public class User {
	public 	String id;
	public String name;
	public Integer age;
	public String email;

}
