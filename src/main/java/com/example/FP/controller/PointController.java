package com.example.FP.controller;

import com.example.FP.dto.PointDto;
import com.example.FP.entity.Point;
import com.example.FP.mapper.PointMapper;
import com.example.FP.service.PointService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PointController {

    private final PointService ps;

    //회원별 포인트 적립,사용 내역 출력화면을 위한 메서드
    @GetMapping("/pointList")
    public String listPoint(Model model, HttpSession session){
        String userid = (String)session.getAttribute("userid");

        model.addAttribute("list",ps.findPointListByUserid(userid));
        return "/user/pointList";

    }

    //적립된 포인트목록 출력을 위한 메서드
    @GetMapping("/pointList/get")
    public String listPointGet(Model model, HttpSession session){
        String userid = (String)session.getAttribute("userid");

        model.addAttribute("list",ps.findGetPointListByUserId(userid));
        return "/user/pointList";

    }
    //사용한 포인트내역 출력을 위한 메서드
    @GetMapping("/pointList/used")
    public String listPointUsed(Model model, HttpSession session){
        String userid = (String)session.getAttribute("userid");

        model.addAttribute("list",ps.findUsedPointListByUserId(userid));
        return "/user/pointList";

    }

}
