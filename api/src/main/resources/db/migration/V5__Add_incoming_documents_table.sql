create table incoming_documents (
    id bigserial primary key not null,
    version bigint,
    title varchar(255) not null,
    address text,
    is_enabled boolean default true
);

insert into incoming_documents (version ,title, address, is_enabled) values
(0, 'Старая11', 'г. Минск, ул. Первая 111', true),
(0, 'Всегда11', 'г. Минск, ул. Вторая 111', true),
(0, 'Новая11', 'г. Минск, ул. Старая 4111', true),
(0, 'Немига секонд11', 'г. Минск, ул. Немига 2111', true),
(0, 'Победителей Нет11', 'г. Минск, пр-т Победителей 404111', true),
(0, 'Зыбицкая11', 'ул. Зыбицкая 40111', true);

