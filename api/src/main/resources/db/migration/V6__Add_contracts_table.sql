/*create table contracts (
    id bigserial primary key not null,
    version bigint,
    contract_date  date not null,
    contract_number  varchar not null,
    contractor_id bigserial  not null,
    description varchar
);
*/

insert into contracts (version ,contract_date, contract_number, contractor_id, description) values
(0, '2025-09-15', 'CAB1234567',1, 'Some contractor dsc');

