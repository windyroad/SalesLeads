
drop table IF EXISTS SalesLeads;

create table IF NOT EXISTS SalesLeads (
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    EMail VARCHAR(255),
    PhoneNumber VARCHAR(255)
);