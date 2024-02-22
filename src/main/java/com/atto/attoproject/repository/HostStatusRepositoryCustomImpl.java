package com.atto.attoproject.repository;

import com.atto.attoproject.data.HostStatusDto;
import com.atto.attoproject.domain.QHost;
import com.atto.attoproject.domain.QHostStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.atto.attoproject.domain.QHost.host;
import static com.atto.attoproject.domain.QHostStatus.hostStatus;


@Repository
public class HostStatusRepositoryCustomImpl implements HostStatusRepositoryCustom {

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    HostStatusRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public HostStatusDto findByCheckId(Long id) {

        return queryFactory
                .select(
                        Projections.fields(HostStatusDto.class
                                ,host.id
                                ,host.ip
                                ,host.name
                                ,hostStatus.alive
                                ,hostStatus.lastStatusCheckeDate
                        )
                )
                .from(hostStatus)
                .leftJoin(hostStatus.host, host)
                .where(hostStatus.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<HostStatusDto> findByAllHostAndHostsStatus() {
        return queryFactory
                .select(
                        Projections.fields(HostStatusDto.class
                                ,host.id
                                ,host.ip
                                ,host.name
                                ,hostStatus.alive
                                ,hostStatus.lastStatusCheckeDate
                        )
                )
                .from(hostStatus)
                .leftJoin(hostStatus.host, host)
                .fetch();
    }
}
