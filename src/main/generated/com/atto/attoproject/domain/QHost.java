package com.atto.attoproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHost is a Querydsl query type for Host
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHost extends EntityPathBase<Host> {

    private static final long serialVersionUID = 2006509052L;

    public static final QHost host = new QHost("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> registeredAt = createDateTime("registeredAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QHost(String variable) {
        super(Host.class, forVariable(variable));
    }

    public QHost(Path<? extends Host> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHost(PathMetadata metadata) {
        super(Host.class, metadata);
    }

}

