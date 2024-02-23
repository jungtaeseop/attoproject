package com.atto.attoproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuditLog is a Querydsl query type for AuditLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuditLog extends EntityPathBase<AuditLog> {

    private static final long serialVersionUID = -524491843L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuditLog auditLog = new QAuditLog("auditLog");

    public final DateTimePath<java.time.LocalDateTime> eventDateTime = createDateTime("eventDateTime", java.time.LocalDateTime.class);

    public final StringPath eventOutcome = createString("eventOutcome");

    public final StringPath eventType = createString("eventType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QAuditLog(String variable) {
        this(AuditLog.class, forVariable(variable), INITS);
    }

    public QAuditLog(Path<? extends AuditLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuditLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuditLog(PathMetadata metadata, PathInits inits) {
        this(AuditLog.class, metadata, inits);
    }

    public QAuditLog(Class<? extends AuditLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

