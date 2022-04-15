create table if not exists `download_file` (
    `id`            bigint unsigned not null auto_increment,
    `created_at`    datetime        not null default current_timestamp,
    `updated_at`    datetime        not null default current_timestamp on update current_timestamp,
    `status`        int unsigned    not null default 0,
    `account_id`    bigint unsigned not null ,
    `link`          varchar(255)    not null ,
    `title`         varchar(255)    not null ,
    `body`          text            not null ,
    `directory`     varchar(255)    not null ,
    `hit`           bigint unsigned not null default 0,
    primary key (`id`)
)
;

CREATE INDEX `index_id_status_account_id`
    ON `download_file` (`id`, `status`, `account_id`);

CREATE INDEX `index_status`
    ON `download_file` (`status`);