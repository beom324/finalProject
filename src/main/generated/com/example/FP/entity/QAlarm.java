package com.example.FP.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarm is a Querydsl query type for Alarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarm extends EntityPathBase<Alarm> {

    private static final long serialVersionUID = 1921561881L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarm alarm = new QAlarm("alarm");

    public final QMember alarmMember;

    public final StringPath alarmMsg = createString("alarmMsg");

    public final QRecipe alarmRecipe;

    public final NumberPath<Integer> alarmState = createNumber("alarmState", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAlarm(String variable) {
        this(Alarm.class, forVariable(variable), INITS);
    }

    public QAlarm(Path<? extends Alarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarm(PathMetadata metadata, PathInits inits) {
        this(Alarm.class, metadata, inits);
    }

    public QAlarm(Class<? extends Alarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.alarmMember = inits.isInitialized("alarmMember") ? new QMember(forProperty("alarmMember")) : null;
        this.alarmRecipe = inits.isInitialized("alarmRecipe") ? new QRecipe(forProperty("alarmRecipe"), inits.get("alarmRecipe")) : null;
    }

}

