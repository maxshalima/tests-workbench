create table warehouses (
    id bigserial primary key not null,
    version bigint,
    title varchar(255) not null,
    address text,
    is_enabled boolean default true
);

insert into warehouses (version ,title, address, is_enabled) values
(0, 'Старая', 'г. Минск, ул. Первая 1', true),
(0, 'Всегда', 'г. Минск, ул. Вторая 2', true),
(0, 'Новая', 'г. Минск, ул. Старая 4', true),
(0, 'Немига секонд', 'г. Минск, ул. Немига 2', true),
(0, 'Победителей Нет', 'г. Минск, пр-т Победителей 404', true),
(0, 'Зыбицкая', 'ул. Зыбицкая 401', true);

