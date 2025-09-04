create table warehouse_item_types (
    id bigserial primary key not null,
    version bigint,
    title varchar(255) not null,
    units varchar(10),
    gtin varchar(32),
    "group" varchar(255) not null,
    subgroup varchar(255)
);
