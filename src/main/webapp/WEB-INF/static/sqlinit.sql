# create schema retentionmanager;
#
# create table `retentionmanager`.`Terms`
# (
#     `id`            int          not null auto_increment,
#     `request_no`    int          null,
#     `order_no`      varchar(255) null,
#     `date_from`     DATETIME     null,
#     `date_to`       DATETIME     null,
#     `amount`        DOUBLE       null,
#     `amount_type`   varchar(255) null,
#     `amount_period` varchar(255) null,
#     `auth_percent`  int          null,
#     `active_flag`   bit          null,
#     `listed_system` varchar(255) null,
#     primary key (`id`),
#     unique index `id_UNIQUE` (`id` asc) visible
# );
#
# create table `retentionmanager`.`Products`
# (
#     id           int          not null auto_increment,
#     client       varchar(255) null,
#     info         varchar(255) null,
#     system_name  varchar(255) null,
#     technologies varchar(255) null,
#     primary key (id),
#     unique index id_UNIQUE (id asc) visible
# );
#
# insert into Products(id, client, info, system_name, technologies)
# values (1, 'Client1', 'System marked as system no1', 'System no1', 'Java'),
#        (2, 'Client1', 'Frontend for system no1', 'front', 'js')
#
# insert into Terms (terms_id, request_no, order_no, date_from, date_to, amount, amount_type, amount_period, auth_percent,
#                    active_flag, listed_system)
# values (1, 1, '1', '2019-02-02', '2019-02-03', 200, 'BRU', 'MONTH', 20, false, 'System'),
#        (2, 2, '2', '2019-02-03', '2019-04-03', 400, 'BRU', 'YEAR', 10, true, 'System'),
#        (3, 4, '4', '2019-06-11', '2020-03-03', 2400, 'BRU', 'YEAR', 10, true, 'System');
#
