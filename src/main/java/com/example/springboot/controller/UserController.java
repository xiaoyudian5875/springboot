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

@Controller
public class UserController {

	@Autowired
	private UserService service;

	/**
	 * 显示所有用户页面
	 * 
	 * @param modl
	 * @return
	 */
	@RequestMapping("/list")
	public String findall(Model modl) {
		List<User> list = service.list(null);
		int count = service.count();
		System.out.println("-------------------------->"+count);
		modl.addAttribute("userlist", list);
		modl.addAttribute("count", count);
		return "main";
	};

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@RequestMapping("/delete/{id}")
	public void deleteone(@PathVariable("id") String id, HttpServletResponse resp) {
		boolean i = service.removeById(id);
		System.out.println(i);
		try {
			resp.getWriter().print(i);
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
	// @ResponseBody
	public String update(@PathVariable("id") String id, Model modl) {
		User user = service.getById(id);
		System.out.println(user);
		modl.addAttribute("user", user);
		return "update";
	}

	/**
	 * 修改前提交;
	 * 
	 * @param id
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
		System.out.println(b);
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
