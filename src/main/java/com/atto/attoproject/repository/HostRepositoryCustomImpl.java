package com.atto.attoproject.repository;

import com.atto.attoproject.data.HostStatusDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.atto.attoproject.domain.QHost.host;
import static com.atto.attoproject.domain.QHostStatus.hostStatus;

@Repository
public class HostRepositoryCustomImpl implements HostRepositoryCustom{
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    HostRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public HostStatusDto findByHostStatusCheckId(Long id) {
        return queryFactory
                .select(
                        Projections.fields(HostStatusDto.class
                                ,host.id
                                ,host.ip
                                ,host.name
                                ,host.status.alive
                                ,host.status.lastStatusCheckeDate
                        )
                )
                .from(host)
                .where(host.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<HostStatusDto> findByAllHostsStatus() {
        return queryFactory
                .select(
                        Projections.fields(HostStatusDto.class
                                ,host.id
                                ,host.ip
                                ,host.name
                                ,host.status.alive
                                ,host.status.lastStatusCheckeDate
                        )
                )
                .from(host)
                .fetch();
    }
}
