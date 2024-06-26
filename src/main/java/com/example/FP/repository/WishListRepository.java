package com.example.FP.repository;

import com.example.FP.entity.WishList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long>, WishListRepositoryCustom {

    List<WishList> findByWishlistMemberIdAndWishlistRecipeId(long memberId, Long recipeId);



    @Modifying
    @Transactional
    @Query("DELETE FROM WishList w WHERE w.wishlistMember.id = :memberId AND w.wishlistRecipe.id = :recipeId")
    void deleteByMemberIdAndRecipeId(Long memberId, Long recipeId);




    @Query("SELECT w.wishlistRecipe.id, COUNT(w.wishlistRecipe.id) as recipeCount " +
            "FROM WishList w " +
            "GROUP BY w.wishlistRecipe.id " +
            "ORDER BY recipeCount DESC")
    List<Object[]> findTopPopularRecipes(Pageable pageable);


}
