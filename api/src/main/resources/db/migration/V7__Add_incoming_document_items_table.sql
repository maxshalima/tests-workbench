create table incoming_document_items (
    id bigserial primary key not null,
    version bigint,
    incoming_document_id bigint not null references incoming_documents(id) on delete cascade,
    warehouse_item_type_id bigint not null references warehouse_item_types(id),
    quantity integer not null check (quantity > 0),
    price numeric(10, 2) not null check (price > 0)
);

insert into incoming_document_items (version, incoming_document_id, warehouse_item_type_id, quantity, price)
values
    (0,1, 1, 100, 5.50),
    (0,1, 2, 50, 12.75),
    (0,1, 3, 200, 1.25),
    (0,1, 4, 222, 1.05);
