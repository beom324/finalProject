package com.example.FP.repository;

import com.example.FP.entity.Point;
import com.example.FP.entity.QPoint;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import java.util.List;

import static com.example.FP.entity.QPoint.point;


public class PointRepositoryImpl implements PointRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public PointRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Point> pointListByUserid(String userid) {

        List<Point> result = queryFactory.select(point).from(point).where(point.point_member.userid.eq(userid)).fetch();
        return result;

    }
}