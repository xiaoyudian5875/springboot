package com.example.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.bean.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
//业务逻辑层
@Controller
public class UserController {

	@Autowired//引用业务接口
	private UserService service;

	/**
	 * 显示所有用户页面
	 * 
	 * @param modl
	 * @return
	 */
	@RequestMapping("/list")//url注解
	public String findall(Model modl) {
		//crud操作
		List<User> list = service.list(null);
		int count = service.count();
		System.out.println("-------------------------->"+count);
		modl.addAttribute("userlist", list);
		//将数据存入modl里
		modl.addAttribute("count", count);
		//返回一个页面
		return "main";
	};

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@RequestMapping("/delete/{id}")//采用简单的传参方式
	public void deleteone(@PathVariable("id") String id, HttpServletResponse resp) {//参数绑定
		boolean i = service.removeById(id);
		System.out.println(i);
		try {
			resp.getWriter().print(i);//返回结构给前端
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改前查询
	 * 
	 * @param id
	 */
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") String id, Model modl) {
		User user = service.getById(id);
		//System.out.println(user);
		modl.addAttribute("user", user);
		return "update";
	}

	/**
	 * 修改前提交;
	 * 
	 * @param
	 */
	// http://localhost:9002/user/update01/?id=2&name=zhangsan&age=19&email=test2%40baomidou.com
	@GetMapping("/update01")
	// public String update01(@RequestParam String id,@RequestParam String
	// name,@RequestParam Integer age ,@RequestParam String email, Model modl){
	public String update01(User user, Model modl) {
		/*
		 * User user = new User(); user.setAge(age); user.setId(id); user.setName(name);
		 * user.setEmail(email);
		 */
		System.out.println(user);
		boolean b = service.updateById(user);
		//System.out.println(b);
		//转发到指定的地址
		return "redirect:/list";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param modl
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model modl) {
		//跳转页面
		return "insert";
	}
	/**
	 * 添加用户提交
	 * @param user
	 * @param modl
	 * @return
	 */
	@RequestMapping("/add")
	public String adduser(User user, Model modl) {
		boolean b = service.save(user);
		System.out.println(b);
		//跳转地址
		return "redirect:/list";
	}

	/**
	 * thymeleaf测试
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("name", "lisi");
		return "hello";
	}

}
