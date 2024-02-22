package com.atto.attoproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHostStatus is a Querydsl query type for HostStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHostStatus extends EntityPathBase<HostStatus> {

    private static final long serialVersionUID = -9554674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHostStatus hostStatus = new QHostStatus("hostStatus");

    public final EnumPath<com.atto.attoproject.domain.enums.Alive> alive = createEnum("alive", com.atto.attoproject.domain.enums.Alive.class);

    public final QHost host;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastStatusCheckeDate = createDateTime("lastStatusCheckeDate", java.time.LocalDateTime.class);

    public QHostStatus(String variable) {
        this(HostStatus.class, forVariable(variable), INITS);
    }

    public QHostStatus(Path<? extends HostStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHostStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHostStatus(PathMetadata metadata, PathInits inits) {
        this(HostStatus.class, metadata, inits);
    }

    public QHostStatus(Class<? extends HostStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.host = inits.isInitialized("host") ? new QHost(forProperty("host"), inits.get("host")) : null;
    }

}

