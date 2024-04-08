package com.example.FP.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = 1925557602L;

    public static final QEvent event = new QEvent("event");

    public final StringPath eventContent = createString("eventContent");

    public final DateTimePath<java.time.LocalDateTime> eventEndDate = createDateTime("eventEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> eventStartDate = createDateTime("eventStartDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> eventSubmitDate = createDateTime("eventSubmitDate", java.time.LocalDateTime.class);

    public final StringPath eventThumbnail = createString("eventThumbnail");

    public final StringPath eventTitle = createString("eventTitle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

