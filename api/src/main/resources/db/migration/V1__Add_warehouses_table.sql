create table warehouses (
    id bigserial primary key not null,
    version bigint,
    title varchar(255) not null,
    address text,
    is_enabled boolean default true
);

insert into warehouses (version ,title, address, is_enabled) values
(0, 'Чайная', 'г. Минск, пр-т Независимости 44, 3-Н', true),
(0, 'Оптовый', 'г. Минск, пр-т Независимости 44, 3-Н', true),
(0, 'Левада', 'г. Минск, ул. Нововиленская 49', true),
(0, 'Куби Немига', 'г. Минск, ул. Немига 3/3', true),
(0, 'Куби Независимости', 'г. Минск, пр-т Независимости 44, 3-Н', true),
(0, '24-гадзінны оўпэнэйр Vulica Brasil на Кастрычніцкай', 'Ул. Октябрьская', true);

