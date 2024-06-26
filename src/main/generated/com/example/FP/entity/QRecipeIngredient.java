package com.example.FP.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeIngredient is a Querydsl query type for RecipeIngredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeIngredient extends EntityPathBase<RecipeIngredient> {

    private static final long serialVersionUID = -1453243849L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeIngredient recipeIngredient = new QRecipeIngredient("recipeIngredient");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIngredient recipeIngredientIngredient;

    public final StringPath recipeIngredientNeed = createString("recipeIngredientNeed");

    public final NumberPath<Integer> recipeIngredientQty = createNumber("recipeIngredientQty", Integer.class);

    public final QRecipe recipeIngredientRecipe;

    public final StringPath recipeIngredientUnit = createString("recipeIngredientUnit");

    public QRecipeIngredient(String variable) {
        this(RecipeIngredient.class, forVariable(variable), INITS);
    }

    public QRecipeIngredient(Path<? extends RecipeIngredient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeIngredient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeIngredient(PathMetadata metadata, PathInits inits) {
        this(RecipeIngredient.class, metadata, inits);
    }

    public QRecipeIngredient(Class<? extends RecipeIngredient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipeIngredientIngredient = inits.isInitialized("recipeIngredientIngredient") ? new QIngredient(forProperty("recipeIngredientIngredient"), inits.get("recipeIngredientIngredient")) : null;
        this.recipeIngredientRecipe = inits.isInitialized("recipeIngredientRecipe") ? new QRecipe(forProperty("recipeIngredientRecipe"), inits.get("recipeIngredientRecipe")) : null;
    }

}

