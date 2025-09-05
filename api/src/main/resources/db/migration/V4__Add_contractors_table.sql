create table contractors(
    id bigserial primary key not null,
    version bigint,
    tax_number varchar(9) not null,
    short_title varchar(255) not null,
    title text not null,
    address text,
    email varchar(255),
    phone varchar(255),
    contract_person varchar(255) not null
);

create table contracts (
    id bigserial primary key not null,
    version bigint,
    contract_date date not null,
    contract_number varchar(255) not null,
    contractor_id bigint not null references contractors,
    description text
);
