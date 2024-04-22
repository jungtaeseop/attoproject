package com.atto.attoproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHostStatus is a Querydsl query type for HostStatus
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHostStatus extends BeanPath<HostStatus> {

    private static final long serialVersionUID = -9554674L;

    public static final QHostStatus hostStatus = new QHostStatus("hostStatus");

    public final EnumPath<com.atto.attoproject.domain.enums.Alive> alive = createEnum("alive", com.atto.attoproject.domain.enums.Alive.class);

    public final DateTimePath<java.time.LocalDateTime> lastStatusCheckeDate = createDateTime("lastStatusCheckeDate", java.time.LocalDateTime.class);

    public QHostStatus(String variable) {
        super(HostStatus.class, forVariable(variable));
    }

    public QHostStatus(Path<? extends HostStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHostStatus(PathMetadata metadata) {
        super(HostStatus.class, metadata);
    }

}

