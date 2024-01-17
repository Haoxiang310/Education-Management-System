package com.ems902.Controller;

import com.alibaba.fastjson.JSON;
import com.ems902.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class LoginController {
    @Resource
    private UserService userService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/checkAccount",method = RequestMethod.GET)
    @ResponseBody
    public String checkAccount(@RequestParam("userid") int id, @RequestParam("kind") int kind, @RequestParam("userpass") String pass, Model model, HttpServletResponse response)
    {
    if(userService.checkAccount(id,kind,pass)==1){
        model.addAttribute("username",userService.getStuNameById(id));
        model.addAttribute("stuId",id);
        model.addAttribute("msg","密码正确，学生登陆成功");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return JSON.toJSONString(model);
    }
    else if (userService.checkAccount(id,kind,pass)==2){
        model.addAttribute("username",userService.getTeaNameById(id));
        model.addAttribute("teaId",id);
        model.addAttribute("msg","密码正确，教师登陆成功");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return JSON.toJSONString(model);
    }
    else if (userService.checkAccount(id,kind,pass)==3){
        model.addAttribute("adminId",id);
        model.addAttribute("msg","密码正确，教务登陆成功");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return JSON.toJSONString(model);
    }
    else {
        model.addAttribute("msg","密码错误");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return JSON.toJSONString(model);
    }
    }
}
