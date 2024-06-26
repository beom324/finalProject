package com.example.FP.repository;

import com.example.FP.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long>, OrdersRepositoryCustom {

    @Query("SELECT o FROM Orders o WHERE o.ordersMember.id = :memberId ORDER BY o.ordersDate DESC, o.id DESC")
    List<Orders> findLatestOrderByMemberIdWithHighestId(@Param("memberId") Long memberId);

    }
