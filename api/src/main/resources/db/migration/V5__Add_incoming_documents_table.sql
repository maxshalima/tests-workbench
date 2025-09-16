create table incoming_documents (
    id bigserial primary key not null,
    version bigint,
    document_date date not null,
    document_number varchar(10) not null,
    contractor_id bigserial,
    contract_id bigserial,
    warehouse_id bigserial
);

insert into incoming_documents (version ,document_date, document_number, contractor_id,contract_id) values
(0, '2025-09-15', 'ABC1234567',1,1);

