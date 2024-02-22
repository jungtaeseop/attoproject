package com.atto.attoproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditLog is a Querydsl query type for AuditLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuditLog extends EntityPathBase<AuditLog> {

    private static final long serialVersionUID = -524491843L;

    public static final QAuditLog auditLog = new QAuditLog("auditLog");

    public final com.atto.attoproject.config.basedomain.QBaseEntity _super = new com.atto.attoproject.config.basedomain.QBaseEntity(this);

    public final StringPath action = createString("action");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath data = createString("data");

    public final StringPath entity = createString("entity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath principal = createString("principal");

    public QAuditLog(String variable) {
        super(AuditLog.class, forVariable(variable));
    }

    public QAuditLog(Path<? extends AuditLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditLog(PathMetadata metadata) {
        super(AuditLog.class, metadata);
    }

}

