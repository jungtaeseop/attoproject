
create table audit_logs (
        id bigserial not null,
        event_date_time timestamp(6),
        event_outcome varchar(255),
        event_type varchar(255),
        user_id bigint,
        primary key (id)
);

create table hosts (
                       id bigserial not null,
                       created_date timestamp(6),
                       last_modified_date timestamp(6),
                       ip varchar(255),
                       name varchar(255),
                       primary key (id)
);

create table host_status (
                             id bigserial not null,
                             alive varchar(255) check (alive in ('Enabled','Disabled')),
                             last_status_checke_date timestamp(6),
                             host_id bigint,
                             primary key (id)
);

create table roles (
                       id serial not null,
                       name varchar(20) check (name in ('ROLE_USER','ROLE_ADMIN')),
                       primary key (id)
);

create table token_blacklist (
                                 id bigserial not null,
                                 created_date timestamp(6),
                                 last_modified_date timestamp(6),
                                 token varchar(255),
                                 primary key (id)
);

create table user_roles (
                            user_id bigint not null,
                            role_id integer not null,
                            primary key (user_id, role_id)
);

create table users (
                       id bigserial not null,
                       created_date timestamp(6),
                       last_modified_date timestamp(6),
                       password varchar(255),
                       user_id varchar(255),
                       user_name varchar(255),
                       primary key (id)
);

alter table if exists hosts
drop constraint if exists UK_dsfuyk1ma448kgx6axegan3q1;

alter table if exists hosts
    add constraint UK_dsfuyk1ma448kgx6axegan3q1 unique (ip);


alter table if exists hosts
drop constraint if exists UK_9g0hg6hkng2b8htbsnmjtpmg3;

alter table if exists hosts
    add constraint UK_9g0hg6hkng2b8htbsnmjtpmg3 unique (name);

alter table if exists host_status
drop constraint if exists UK_khxrptwjk2mike185jqm8n5uy;

alter table if exists host_status
    add constraint UK_khxrptwjk2mike185jqm8n5uy unique (host_id);


alter table if exists audit_logs
    add constraint FKjs4iimve3y0xssbtve5ysyef0
    foreign key (user_id)
    references users;


alter table if exists host_status
    add constraint FK6ckpy5q8cc30b8k3rq02tvfyk
    foreign key (host_id)
    references hosts;


alter table if exists user_roles
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6
    foreign key (role_id)
    references roles;


alter table if exists user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
    foreign key (user_id)
    references users;


insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');