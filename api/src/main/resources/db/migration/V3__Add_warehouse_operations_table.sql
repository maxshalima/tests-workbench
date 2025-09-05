create table warehouse_operations (
    id bigserial primary key not null,
    version bigint,
    warehouse_id bigint not null references warehouses,
    item_type_id bigint not null references warehouse_item_types,
    operation_type varchar(64) not null,
    operation_state varchar(32) not null,
    owner_doc_type varchar(255) not null,
    owner_doc_id bigint not null,
    owner_doc_info text,
    operation_date date not null,
    quantity numeric(24,8) not null default 0.0,
    price numeric(21,5) not null default 0.0,
    total_sum numeric(18,2) not null default 0.0,
    source_op_id bigint references warehouse_operations,
    reference_op_id bigint references warehouse_operations,
    remaining_quantity numeric(24,8),
    calculated_costs numeric(21,5)
)