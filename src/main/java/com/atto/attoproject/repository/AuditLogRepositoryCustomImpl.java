package com.atto.attoproject.repository;

import static com.atto.attoproject.domain.QAuditLog.auditLog;
import static com.atto.attoproject.domain.QUser.user;

import com.atto.attoproject.data.AuditLogDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AuditLogRepositoryCustomImpl implements AuditLogRepositoryCustom{
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    AuditLogRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<AuditLogDto> findAllAuditLogDto() {

        return queryFactory
                .select(
                        Projections.fields(AuditLogDto.class
                                ,auditLog.id.as("auditlog_id")
                                ,user.userId
                                ,user.username
                                ,auditLog.eventDateTime
                                ,auditLog.eventType
                                ,auditLog.eventOutcome
                        )
                )
                .from(auditLog)
                .leftJoin(auditLog.user, user)
                .fetch();
    }
}
