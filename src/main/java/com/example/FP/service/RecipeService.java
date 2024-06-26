package com.example.FP.service;

import com.example.FP.entity.Recipe;
import com.example.FP.entity.WishList;
import com.example.FP.repository.RecipeRepository;
import com.example.FP.repository.WishListRepository;
import com.example.FP.dto.RecipeDto;
import com.example.FP.dto.RecipeIngredientDto;
import com.example.FP.dto.RecipeOrderDto;
import com.example.FP.entity.*;
import com.example.FP.mapper.RecipeIngredientMapper;
import com.example.FP.mapper.RecipeOrderMapper;
import com.example.FP.mapper.RepiceMapper;
import com.example.FP.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.io.File;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {


    private final RecipeRepository rr;
    private final WishListRepository wr;
    private final RecipeIngredientRepository rir;
    private final RecipeOrderRepository ror;
    private final MemberService ms;
    private final RecipeCategoryRepository rcr;
    private final IngredientRepository ir;

    // 레시피 목록 불러오기
    public List<Recipe> findAll() {

        return rr.findAll();
    }

    //레시피 목록 카테고리+검색+ 페이지네이션//

    public Page<Recipe> listRecipes(String keyword,Long categoryId,Pageable pageable) {
        Page<Recipe> recipesList =
        rr.findByTitleContainingAndCategory(keyword, categoryId, pageable);

        return recipesList;
    }

    //레시피 목록 카테고리+검색+ 페이지네이션 + 맴버id//
    public Page<Recipe> listRecipes(String keyword,Long categoryId, Long memberId, Pageable pageable) {
        Page<Recipe> recipesList =
                rr.findByTitleContainingAndCategoryAndMemberId(keyword, categoryId, memberId,pageable);

        return recipesList;
    }

    //레시피 전체 목록 + 페이지네이션 + 검색
    public Page<Recipe> listAll(Pageable pageable, String keyword) {
        return rr.findByTitleContaining(keyword, pageable);
    }


    // 위시리스트에서 가장 많은 찜을 받은 레시피를 가져옴
    public List<Recipe> listTop4() {
        List<Object[]> top4Data = wr.findTopPopularRecipes(PageRequest.of(0, 4));
        List<Long> recipeIds = top4Data.stream()
                .map(entry -> (Long) entry[0]) // Object[]의 첫 번째 요소를 Long으로 캐스팅
                .collect(Collectors.toList());
        return rr.findByIdIn(recipeIds);
    }

    // 이 레시피 어때요?
    public Recipe HowAbout(){
        //총 레시피의 수를 구함
        long totalRecipe =  rr.count();
        Random r = new Random();
        //랜덤으로 레시피를 하나 가져옴. (1부터 총갯수만큼)
        Long randomId = r.nextLong(totalRecipe +1 );
              Optional<Recipe> optional = rr.findById(randomId);
        Recipe recipe =optional.get();
            return recipe;
    }









    // 메인페이지 5개 보여줄 레시피
    public List<Recipe> top4() {
        return rr.findTop4ByOrderByRecipeViewsDesc();
    }

    // 랜덤으로 레시피 5개 리턴
    public List<Recipe> randomList() {
        List<Recipe> list = rr.findAll();
        Collections.shuffle(list);
        List<Recipe> randomRecipes = list.subList(0, Math.min(4, list.size()));
        return randomRecipes;
    }

    @Transactional
    public Long saveRecipe(Map<String, Object> recipeDataList, String userid) {
        RecipeDto recipeDto = null;
        Long recipeId = null;
        Member member = ms.findById(userid);

        try {
            // Jackson ObjectMapper 객체 생성
            ObjectMapper mapper = new ObjectMapper();

            // JSON 문자열을 Map 객체로 변환
            Map<String, Object> jsonMap = mapper.readValue(recipeDataList.get("recipeDataList").toString(), Map.class);

            // 필요한 데이터 추출
            List<Map<String, Object>> ingredientDataList = (List<Map<String, Object>>) jsonMap.get("ingredientDataList");
            List<Map<String, Object>> stepDataList = (List<Map<String, Object>>) jsonMap.get("stepDataList");
            String recipeThumbnail = jsonMap.get("recipeThumbnail").toString();
            String recipeTitle = jsonMap.get("recipeTitle").toString();
            String recipeUrl = null;
            if(jsonMap.get("recipeUrl")!=null){
                if(!jsonMap.get("recipeUrl").toString().equals("")){
                    recipeUrl=jsonMap.get("recipeUrl").toString();
                }
            }
            Long recipeCategoryId = Long.parseLong(jsonMap.get("recipeCategory").toString());
//            update라면 해당 recipeId를 넣고 새로운 요리순서, 재료목록을 넣기위해 기존건 다 삭제해준다.
            if (jsonMap.get("recipeId") != null) {
                recipeId = Long.parseLong(jsonMap.get("recipeId").toString());
                Long recipeIngredientId = rir.PreviousRecipeIngredientMaxId(recipeId);
                rir.deleteAllByPreviousRecipeIngredient(recipeId,recipeIngredientId);

                Long recipeOrderId = ror.PreviousRecipeOrderMaxId(recipeId);
                ror.deleteAllByPreviousRecipeOrder(recipeId,recipeOrderId);
            }
            RecipeCategory recipeCategory = rcr.findById(recipeCategoryId).get();

            recipeDto = new RecipeDto(recipeId, recipeTitle, member.getUserid(), recipeUrl, recipeThumbnail, 0, recipeCategory, member);
            Recipe recipe = RepiceMapper.toEntity(recipeDto);
            rr.save(recipe);

            for (Map<String, Object> i : ingredientDataList) {
                Long ingredientId = Long.parseLong(i.get("recipeIngredientId").toString());
                Ingredient ingredient = ir.findById(ingredientId).get();
                RecipeIngredientDto rid = new RecipeIngredientDto(
                        i.get("recipeIngredientQty").toString(),
                        i.get("recipeIngredientNeed").toString(),
                        recipe,
                        ingredient,
                        i.get("recipeIngredientUnit").toString()
                );
                RecipeIngredient recipeIngredient = RecipeIngredientMapper.toEntity(rid);
                rir.save(recipeIngredient);
            }

            for (Map<String, Object> s : stepDataList) {
                RecipeOrderDto recipeOrderDto = new RecipeOrderDto(
                        s.get("recipeDetail").toString(),
                        s.get("recipePhoto").toString(),
                        recipe);
                RecipeOrder recipeOrder = RecipeOrderMapper.toEntity(recipeOrderDto);
                ror.save(recipeOrder);
            }
            return recipe.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
//    public Map<String,Object> detailRecipe(Long id){
//        Map<String,Object> recipeDetail = new HashMap<String,Object>();
//        List<RecipeIngredient> recipeIngredient = rir.findAllByRecipeIngredientRecipe();
//
//        recipeDetail.put("recipe",rr.findById(id).get());
//        recipeDetail.put("recipeIngredient",recipeIngredient);
//        recipeDetail.put("recipe",rr.findById(id).get());
//        recipeDetail.put("recipe",rr.findById(id).get());
//
//        return recipeDetail;
//    }

    @Transactional
    public Recipe detailRecipe(Long id) {
        rr.UpdateRecipeViews(id);
        return rr.findById(id).get();
    }

    @Transactional
    public void deleteRecipe(Long recipeId) {
        String fileRoot = "src/main/resources/static/images/";    //저장될 외부 파일 경로
        Recipe recipe = rr.findById(recipeId).get();
        try {
            // 순서의 사진들 삭제
            for (RecipeOrder ro : recipe.getRecipeRecipeOrderList()) {
                File file = new File(fileRoot + ro.getRecipePhoto().toString());
                file.delete();
            }
            // 썸네일 삭제
            File file = new File(fileRoot + recipe.getRecipeThumbnail().toString());
            file.delete();
        } catch (Exception e) {
//            System.out.println("레시피 삭제 사진 삭제 중 예외발생 : " + e.getMessage());
        }
        Long recipeIngredientId = rir.PreviousRecipeIngredientMaxId(recipeId);
        rir.deleteAllByPreviousRecipeIngredient(recipeId,recipeIngredientId);
        
        Long recipeOrderId = ror.PreviousRecipeOrderMaxId(recipeId);
        ror.deleteAllByPreviousRecipeOrder(recipeId,recipeOrderId);
        rr.deleteById(recipeId);
    }
}