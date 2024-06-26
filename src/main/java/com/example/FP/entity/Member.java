package com.example.FP.entity;

import com.example.FP.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    @Id@GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false)
    private String userid;
    private String password;
    private String name;
    private String nickname;
    private String addr;
    private String email;
    private String phone;
    private Integer point;
    private String birth;
    private String image;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder.Default
    @OneToMany(mappedBy = "inquiryMember")
    private List<Inquiry> inquiryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "wishlistMember")
    private List<WishList> wishlistList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipeMember")
    private List<Recipe> recipeList = new ArrayList<>();

    @Builder.Default

    @OneToMany(mappedBy = "ordersDetailsMember")
    private List<OrderDetails> orderDetailsMemberList = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "cartMember")
    private List<Cart> memberCartList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "ordersMember")
    private List<Orders> memberOrdersList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "replyMember")
    private List<Reply> memberReplyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "alarmMember")
    private List<Alarm> memberAlarmList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pointMember")
    private List<Point> memberPointList= new ArrayList<>();

    public Member(String userid, String password, String name, String nickname, String addr, String email, String phone, int point, String birth, MemberRole role, List<Inquiry> inquiryList, List<WishList> wishlistList, List<Recipe> recipeList, List<OrderDetails> orderDetailsMemberList, List<Cart> memberCartList, List<Reply> memberReplyList , List<Alarm> memberAlarmList,List<Point> memberPointList,List<Orders> memberOrdersList, String image) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.addr = addr;
        this.email = email;
        this.phone = phone;
        this.point = point;
        this.birth = birth;
        this.role = role;
        this.inquiryList = inquiryList;
        this.wishlistList = wishlistList;
        this.recipeList = recipeList;
        this.orderDetailsMemberList = orderDetailsMemberList;
        this.memberCartList = memberCartList;
        this.memberReplyList = memberReplyList;
        this.memberAlarmList = memberAlarmList;
        this.memberPointList = memberPointList;
        this.memberOrdersList = memberOrdersList;
        this.image = image;
    }

    public Member(String name, Integer point) {
        this.name = name;
        this.point = point;
    }

    public Member(Long id, String password, String nickname, String addr) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.addr = addr;
    }

    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = Member.builder()
                .userid(memberDto.getUserid())
                .name(memberDto.getName())
                .nickname(memberDto.getNickname())
                .addr(memberDto.getAddr())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .birth(memberDto.getBirth())
                .point(0)
                .password(passwordEncoder.encode(memberDto.getPassword()))  //암호화처리
                .image("member.jpg")
                .role(MemberRole.MEMBER)
                .build();
        return member;
    }
    public static Member changeMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = Member.builder()
                .id(memberDto.getId())
                .userid(memberDto.getUserid())
                .name(memberDto.getName())
                .nickname(memberDto.getNickname())
                .addr(memberDto.getAddr())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .birth(memberDto.getBirth())
                .point(0)
                .password(passwordEncoder.encode(memberDto.getPassword()))  //암호화처리
                .role(MemberRole.MEMBER)
                .build();
        return member;
    }

    public static Member createMember(MemberDto memberDto){
        Member member = Member.builder()
                .userid(memberDto.getEmail())
                .name(memberDto.getName())
                .nickname(memberDto.getNickname())
                .email(memberDto.getEmail())
                .birth(memberDto.getBirth())
                .phone(memberDto.getPhone())
                .point(0)
                .image("member.jpg")
                .role(MemberRole.MEMBER)
                .build();
        return member;
    }


    public void newPwd(String password){
        this.password = password;
    }

    public void addPoint(Orders orders){
        int savedPoint = (int)Math.round(orders.getOrdersSalePrice()*0.01);
        this.point += savedPoint;
//        System.out.println(this.getUserid()+" 님의 포인트 "+savedPoint + "원이 적립되었습니다");

    }

    public void usePoint(Orders orders){
        int usedPoint = orders.getOrdersUsedPoint();
        this.point-=usedPoint;
//        System.out.println(this.getUserid()+" 님의 포인트 "+usedPoint+"원이 사용되었습니다");
    }

    public void changeMember(String password, String nickname, String addr){
        this.password = password;
        this.nickname = nickname;
        this.addr =addr;
    }

    public void cancelOrderMember(Orders orders){
        int returnUsePoint = orders.getOrdersUsedPoint();
        int returnAddPoint = (int)Math.round(orders.getOrdersSalePrice()*0.01);
        this.point += -1*(returnAddPoint);
        this.point += (returnUsePoint*-1);
    }



}
