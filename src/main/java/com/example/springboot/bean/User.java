package com.example.springboot.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data//注解包
@TableName("`user`")//表名映射
@Accessors(chain=true)//连续书写
@NoArgsConstructor//无参构造
@ToString//tostring方法
public class User {
	public 	String id;
	public String name;
	public Integer age;
	public String email;

}
