package com.my.github.majiang.community.mytestcommunity.controller;

import com.my.github.majiang.community.mytestcommunity.mapper.QuestionMapper;
import com.my.github.majiang.community.mytestcommunity.mapper.UserMapper;
import com.my.github.majiang.community.mytestcommunity.model.Question;
import com.my.github.majiang.community.mytestcommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
public class PublishController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title == null || title.length() == 0) {
            model.addAttribute("error","标题不能为空");
        }
        if (description == null || description.length() == 0) {
            model.addAttribute("error","问题补充不能为空");
        }
        if (tag == null || tag.length() == 0) {
            model.addAttribute("error","标签不能为空");
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie: cookies) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if (user == null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question()
                .setTitle(title)
                .setDescription(description)
                .setTag(tag)
                .setCreator(user.getId())
                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                .setUpdateTime(new Timestamp(System.currentTimeMillis()));
        questionMapper.insert(question);
        return "redirect:/";
    }
}
