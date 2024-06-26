package com.example.FP.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = 477677400L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final QIngredient cartIngredient;

    public final QMember cartMember;

    public final QRecipe cartRecipe;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cartIngredient = inits.isInitialized("cartIngredient") ? new QIngredient(forProperty("cartIngredient"), inits.get("cartIngredient")) : null;
        this.cartMember = inits.isInitialized("cartMember") ? new QMember(forProperty("cartMember")) : null;
        this.cartRecipe = inits.isInitialized("cartRecipe") ? new QRecipe(forProperty("cartRecipe"), inits.get("cartRecipe")) : null;
    }

}

