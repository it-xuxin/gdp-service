create table sys_dept
(
    id           bigint auto_increment comment '主键'
        primary key,
    name         varchar(64)  default '' null comment '部门名称',
    parent_id    bigint       default 0  null comment '父节点id',
    tree_path    varchar(255) default '' null comment '父节点id路径',
    sort         int          default 0  null comment '显示顺序',
    status       tinyint(1)   default 1  null comment '状态：1-正常 0-禁用',
    deleted      tinyint(1)   default 0  null comment '删除状态：1-删除 0-未删除',
    gmt_create   datetime                null comment '创建时间',
    gmt_modified datetime                null comment '更新时间'
)
    comment '部门表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_dept (id, name, parent_id, tree_path, sort, status, deleted, gmt_create, gmt_modified) VALUES (1, '有来技术', 0, '0', 1, 1, 0, null, null);
INSERT INTO gdp.sys_dept (id, name, parent_id, tree_path, sort, status, deleted, gmt_create, gmt_modified) VALUES (2, '研发部门', 1, '0,1', 1, 1, 0, null, '2022-04-19 12:46:37');
INSERT INTO gdp.sys_dept (id, name, parent_id, tree_path, sort, status, deleted, gmt_create, gmt_modified) VALUES (3, '测试部门', 1, '0,1', 2, 1, 0, null, null);


create table sys_dict
(
    id           bigint auto_increment comment '主键 '
        primary key,
    name         varchar(50) default '' null comment '类型名称',
    code         varchar(50) default '' null comment '类型编码',
    status       tinyint(1)  default 0  null comment '状态（0-正常 ,1-停用）',
    remark       varchar(255)           null comment '备注',
    gmt_create   datetime               null comment '创建时间',
    gmt_modified datetime               null comment '更新时间',
    constraint type_code
        unique (code)
)
    comment '字典类型表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_dict (id, name, code, status, remark, gmt_create, gmt_modified) VALUES (1, '性别', 'gender', 1, null, '2019-12-06 19:03:32', '2021-02-08 14:58:01');
INSERT INTO gdp.sys_dict (id, name, code, status, remark, gmt_create, gmt_modified) VALUES (2, '授权方式', 'grant_type', 1, null, '2020-10-17 08:09:50', '2021-01-31 09:48:24');
INSERT INTO gdp.sys_dict (id, name, code, status, remark, gmt_create, gmt_modified) VALUES (3, '微服务列表', 'micro_service', 1, null, '2021-06-17 00:13:43', '2021-06-17 00:17:22');
INSERT INTO gdp.sys_dict (id, name, code, status, remark, gmt_create, gmt_modified) VALUES (4, '请求方式', 'request_method', 1, null, '2021-06-17 00:18:07', '2021-06-17 00:18:07');


create table sys_dict_item
(
    id           bigint auto_increment comment '主键'
        primary key,
    name         varchar(50)  default '' null comment '字典项名称',
    value        varchar(50)  default '' null comment '字典项值',
    dict_code    varchar(50)  default '' null comment '字典编码',
    sort         int          default 0  null comment '排序',
    status       tinyint(1)   default 0  null comment '状态（0 停用 1正常）',
    defaulted    tinyint(1)   default 0  null comment '是否默认（0否 1是）',
    remark       varchar(255) default '' null comment '备注',
    gmt_create   datetime                null comment '创建时间',
    gmt_modified datetime                null comment '更新时间'
)
    comment '字典数据表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (1, '男', '1', 'gender', 1, 1, 0, null, '2019-05-05 13:07:52', '2019-07-02 14:23:05');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (2, '女', '2', 'gender', 2, 1, 0, null, '2019-04-19 11:33:00', '2019-07-02 14:23:05');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (3, '未知', '0', 'gender', 1, 1, 0, null, '2020-10-17 08:09:31', '2020-10-17 08:09:31');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (6, '密码模式', 'password', 'grant_type', 1, 1, 0, null, '2020-10-17 09:11:52', '2021-01-31 09:48:18');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (7, '授权码模式', 'authorization_code', 'grant_type', 1, 1, 0, null, '2020-10-17 09:12:15', '2020-12-14 10:11:00');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (8, '客户端模式', 'client_credentials', 'grant_type', 1, 1, 0, null, '2020-10-17 09:12:36', '2020-12-14 10:11:00');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (9, '刷新模式', 'refresh_token', 'grant_type', 1, 1, 0, null, '2020-10-17 09:12:57', '2021-01-08 17:33:12');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (10, '简化模式', 'implicit', 'grant_type', 1, 1, 0, null, '2020-10-17 09:13:23', '2020-12-14 10:11:00');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (11, '系统服务', 'youlai-admin', 'micro_service', 1, 1, 0, null, '2021-06-17 00:14:12', '2021-06-17 00:14:12');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (12, '会员服务', 'youlai-ums', 'micro_service', 2, 1, 0, null, '2021-06-17 00:15:06', '2021-06-17 00:15:06');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (13, '商品服务', 'youlai-pms', 'micro_service', 3, 1, 0, null, '2021-06-17 00:15:26', '2021-06-17 00:16:18');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (14, '订单服务', 'youlai-oms', 'micro_service', 4, 1, 0, null, '2021-06-17 00:15:40', '2021-06-17 00:16:10');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (15, '营销服务', 'youlai-sms', 'micro_service', 5, 1, 0, null, '2021-06-17 00:16:01', '2021-06-17 00:16:01');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (16, '不限', '*', 'request_method', 1, 1, 0, null, '2021-06-17 00:18:34', '2021-06-17 00:18:34');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (17, 'GET', 'GET', 'request_method', 2, 1, 0, null, '2021-06-17 00:18:55', '2021-06-17 00:18:55');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (18, 'POST', 'POST', 'request_method', 3, 1, 0, null, '2021-06-17 00:19:06', '2021-06-17 00:19:06');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (19, 'PUT', 'PUT', 'request_method', 4, 1, 0, null, '2021-06-17 00:19:17', '2021-06-17 00:19:17');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (20, 'DELETE', 'DELETE', 'request_method', 5, 1, 0, null, '2021-06-17 00:19:30', '2021-06-17 00:19:30');
INSERT INTO gdp.sys_dict_item (id, name, value, dict_code, sort, status, defaulted, remark, gmt_create, gmt_modified) VALUES (21, 'PATCH', 'PATCH', 'request_method', 6, 1, 0, null, '2021-06-17 00:19:42', '2021-06-17 00:19:42');


create table sys_menu
(
    id           bigint auto_increment
        primary key,
    parent_id    bigint                  null comment '父菜单ID',
    name         varchar(64)  default '' null comment '菜单名称',
    path         varchar(128) default '' null comment '路由路径(浏览器地址栏路径)',
    component    varchar(128)            null comment '组件路径(vue页面完整路径，省略.vue后缀)',
    icon         varchar(64)  default '' null comment '菜单图标',
    sort         int          default 0  null comment '排序',
    visible      tinyint(1)   default 1  null comment '状态(0:禁用;1:开启)',
    redirect     varchar(128) default '' null comment '跳转路径',
    gmt_create   datetime                null comment '创建时间',
    gmt_modified datetime                null comment '更新时间',
    type         varchar(255)            null comment '菜单类型(1:菜单;2:目录;3:外链)'
)
    comment '菜单管理' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (1, 0, '系统管理', '/system', 'Layout', 'system', 1, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '2');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (2, 1, '用户管理', 'user', 'system/user/index', 'user', 1, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (3, 1, '角色管理', 'role', 'system/role/index', 'role', 2, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (4, 1, '菜单管理', 'cmenu', 'system/menu/index', 'menu', 3, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (5, 1, '部门管理', 'dept', 'system/dept/index', 'tree', 4, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (6, 1, '字典管理', 'dict', 'system/dict/index', 'education', 5, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (7, 1, '客户端管理', 'client', 'system/client/index', 'client', 6, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (9, 0, '营销管理', '/sms', 'Layout', 'number', 5, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '2');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (10, 9, '广告管理', 'advert', 'sms/advert/index', 'advert', 1, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (11, 0, '商品管理', '/pms', 'Layout', 'goods', 2, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (12, 11, '商品列表', 'goods', 'pms/goods/index', 'goods-list', 1, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (13, 0, '订单管理', '/oms', 'Layout', 'shopping', 3, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (14, 13, '订单列表', 'order', 'oms/order/index', 'order', 1, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (15, 0, '会员管理', '/ums', 'Layout', 'user', 4, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (16, 15, '会员列表', 'member', 'ums/member/index', 'peoples', 1, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (17, 11, '品牌管理', 'brand', 'pms/brand/index', 'brand', 5, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (18, 11, '商品分类', 'category', 'pms/category/index', 'menu', 3, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (19, 11, '商品上架', 'goods-detail', 'pms/goods/detail', 'publish', 2, 1, null, '2021-08-28 09:12:21', '2021-08-28 09:12:21', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (20, 0, '多级菜单', '/multi-level-menu', 'Layout', 'nested', 7, 1, '/nested/level1/level2', '2022-02-16 23:11:00', '2022-02-16 23:11:00', '2');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (21, 20, '菜单一级', 'nested_level1_index', 'nested/level1/index', '', 1, 1, '/nested/level1/level2', '2022-02-16 23:13:38', '2022-02-16 23:13:38', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (22, 21, '菜单二级', 'nested_level1_level2_index', 'nested/level1/level2/index', '', 1, 1, '/nested/level1/level2/level3', '2022-02-16 23:14:23', '2022-02-16 23:14:23', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (23, 22, '菜单三级-1', 'nested_level1_level2_level3_index1', 'nested/level1/level2/level3/index1', '', 1, 1, '', '2022-02-16 23:14:51', '2022-02-16 23:14:51', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (24, 22, '菜单三级-2', 'nested_level1_level2_level3_index2', 'nested/level1/level2/level3/index2', '', 2, 1, '', '2022-02-16 23:15:08', '2022-02-16 23:15:08', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (26, 0, '外部链接', '/external-link', 'Layout', 'link', 9, 1, '', '2022-02-17 22:51:20', '2022-02-17 22:51:20', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (30, 26, 'document', 'https://www.cnblogs.com/haoxianrui/', '', 'link', 1, 1, '', '2022-02-18 00:01:40', '2022-02-18 00:01:40', '3');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (32, 0, '实验室', '/lab', 'Layout', 'lab', 8, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', '2');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (33, 32, 'Seata', 'seata', 'lab/seata/index', 'security', 1, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', '1');
INSERT INTO gdp.sys_menu (id, parent_id, name, path, component, icon, sort, visible, redirect, gmt_create, gmt_modified, type) VALUES (34, 32, 'RabbitMQ', 'rabbitmq', 'lab/rabbit/index', 'rabbitmq', 2, 1, '', '2022-04-19 09:38:25', '2022-04-19 09:38:25', '1');


create table sys_oauth_client
(
    client_id               varchar(256)  not null
        primary key,
    resource_ids            varchar(256)  null,
    client_secret           varchar(256)  null,
    scope                   varchar(256)  null,
    authorized_grant_types  varchar(256)  null,
    web_server_redirect_uri varchar(256)  null,
    authorities             varchar(256)  null,
    access_token_validity   int           null,
    refresh_token_validity  int           null,
    additional_information  varchar(4096) null,
    autoapprove             varchar(256)  null
)
    collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('client', '', '123456', 'all', 'password,refresh_token', null, null, 3600, 7200, null, 'true');
INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('gdp-admin-web', null, 'LKg7icFR/nbIWATvAoqYEyyoO4nBUf52', 'all', 'password,refresh_token,captcha', '', null, 3600, 7200, null, 'true');
INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('gdp-app', null, 'LKg7icFR/nbIWATvAoqYEyyoO4nBUf52', 'all', 'sms_code,refresh_token', null, null, 3600, 7200, null, 'true');
INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('mall-admin-web', '', '123456', 'all', 'password,refresh_token,captcha', null, '', 3600, 7200, null, 'true');
INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('mall-app', '', '123456', 'all', 'sms_code,refresh_token', null, null, 3600, 7200, null, 'true');
INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('mall-weapp', '', '123456', 'all', 'wechat,refresh_token', null, null, 3600, 7200, null, 'true');
INSERT INTO gdp.sys_oauth_client (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('youlai-admin', '', '123456', 'all', 'password,client_credentials,refresh_token,authorization_code', null, '', 3600, 7200, null, 'true');


create table sys_permission
(
    id           bigint auto_increment comment '主键'
        primary key,
    name         varchar(64)  null comment '权限名称',
    menu_id      int          null comment '菜单模块ID
',
    url_perm     varchar(128) null comment 'URL权限标识',
    btn_perm     varchar(64)  null comment '按钮权限标识',
    gmt_create   datetime     null,
    gmt_modified datetime     null
)
    comment '权限表' collate = utf8mb4_general_ci;

create index id
    on sys_permission (id, name);

create index id_2
    on sys_permission (id, name);

INSERT INTO gdp.sys_permission (id, name, menu_id, url_perm, btn_perm, gmt_create, gmt_modified) VALUES (1, '查看用户', 2, 'GET:/youlai-admin/api/v1/users/*', 'sys:user:view', '2021-02-02 14:16:07', '2021-06-16 22:25:24');
INSERT INTO gdp.sys_permission (id, name, menu_id, url_perm, btn_perm, gmt_create, gmt_modified) VALUES (2, '编辑用户', 2, 'PUT:/youlai-admin/users/*', 'sys:user:edit', '2021-06-16 16:19:44', '2021-06-16 23:36:53');
INSERT INTO gdp.sys_permission (id, name, menu_id, url_perm, btn_perm, gmt_create, gmt_modified) VALUES (3, '新增用户', 2, 'POST:/youlai-admin/api/v1/users', 'sys:user:add', '2021-06-16 23:36:37', '2021-06-16 23:37:03');
INSERT INTO gdp.sys_permission (id, name, menu_id, url_perm, btn_perm, gmt_create, gmt_modified) VALUES (4, '删除用户', 2, 'DELETE:/youlai-admin/api/v1/users/*', 'sys:user:delete', '2021-06-16 23:43:54', '2021-06-16 23:43:54');
INSERT INTO gdp.sys_permission (id, name, menu_id, url_perm, btn_perm, gmt_create, gmt_modified) VALUES (5, '路由列表', 4, 'GET:/youlai-admin/api/v1/menus/route', 'sys:route:query', null, null);


create table sys_role
(
    id           bigint auto_increment
        primary key,
    name         varchar(64) default '' not null comment '角色名称',
    code         varchar(32)            null comment '角色编码',
    sort         int                    null comment '显示顺序',
    status       tinyint(1)  default 1  null comment '角色状态：0-正常；1-停用',
    deleted      tinyint(1)  default 0  not null comment '逻辑删除标识：0-未删除；1-已删除',
    gmt_create   datetime               null comment '更新时间',
    gmt_modified datetime               null comment '创建时间',
    constraint name
        unique (name)
)
    comment '角色表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_role (id, name, code, sort, status, deleted, gmt_create, gmt_modified) VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, '2021-05-21 14:56:51', '2018-12-23 16:00:00');
INSERT INTO gdp.sys_role (id, name, code, sort, status, deleted, gmt_create, gmt_modified) VALUES (2, '系统管理员', 'ADMIN', 2, 1, 0, '2021-03-25 12:39:54', '2018-12-23 16:00:00');
INSERT INTO gdp.sys_role (id, name, code, sort, status, deleted, gmt_create, gmt_modified) VALUES (3, '访问游客', 'GUEST', 3, 1, 0, '2021-05-26 15:49:05', '2019-05-05 16:00:00');


create table sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID'
)
    comment '角色和菜单关联表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 1);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 2);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 3);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 4);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 5);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 6);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 7);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 11);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 19);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 18);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 17);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 12);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 13);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 14);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 15);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 16);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 9);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 10);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 20);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 21);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 22);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 24);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 26);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 30);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 32);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 33);
INSERT INTO gdp.sys_role_menu (role_id, menu_id) VALUES (2, 34);


create table sys_role_permission
(
    role_id       int null comment '角色id',
    permission_id int null comment '资源id'
)
    comment '角色权限表' collate = utf8mb4_general_ci;

create index permission_id
    on sys_role_permission (permission_id);

create index role_id
    on sys_role_permission (role_id);

INSERT INTO gdp.sys_role_permission (role_id, permission_id) VALUES (2, 1);
INSERT INTO gdp.sys_role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO gdp.sys_role_permission (role_id, permission_id) VALUES (2, 3);
INSERT INTO gdp.sys_role_permission (role_id, permission_id) VALUES (2, 5);
INSERT INTO gdp.sys_role_permission (role_id, permission_id) VALUES (2, 4);


create table sys_user
(
    id           int auto_increment
        primary key,
    username     varchar(64)             null comment '用户名',
    nickname     varchar(64)             null comment '昵称',
    gender       tinyint(1)   default 1  null comment '性别：1-男 2-女',
    password     varchar(100)            null comment '密码',
    dept_id      int                     null comment '部门ID',
    avatar       varchar(255) default '' null comment '用户头像',
    mobile       varchar(20)             null comment '联系方式',
    status       tinyint(1)   default 1  null comment '用户状态：1-正常 0-禁用',
    email        varchar(128)            null comment '用户邮箱',
    deleted      tinyint(1)   default 0  null comment '逻辑删除标识：0-未删除；1-已删除',
    gmt_create   datetime                null comment '创建时间',
    gmt_modified datetime                null comment '更新时间',
    constraint login_name
        unique (username)
)
    comment '用户信息表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_user (id, username, nickname, gender, password, dept_id, avatar, mobile, status, email, deleted, gmt_create, gmt_modified) VALUES (1, 'root', '有来技术', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', null, '', '17621590365', 1, 'youlaitech@163.com', 0, null, null);
INSERT INTO gdp.sys_user (id, username, nickname, gender, password, dept_id, avatar, mobile, status, email, deleted, gmt_create, gmt_modified) VALUES (2, 'admin', '系统管理员', 1, '$2a$10$yJSqqr6sTxNuYtA6EKcVUe2I4USFCzJ29sNcRrBvtAkSYcNg5ydQ6', 2, 'https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif', '17621210366', 1, 'youlaitech@163.com', 0, '2019-10-10 13:41:22', '2021-06-06 23:41:35');
INSERT INTO gdp.sys_user (id, username, nickname, gender, password, dept_id, avatar, mobile, status, email, deleted, gmt_create, gmt_modified) VALUES (3, 'test', '测试小用户', 1, '$2a$10$MPJkNw.hKT/fZOgwYP8q9eu/rFJJDsNov697AmdkHNJkpjIpVSw2q', 3, 'https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif', '17621210366', 1, 'youlaitech@163.com', 0, '2021-06-05 01:31:29', '2021-06-05 01:31:29');


create table sys_user_role
(
    user_id int not null comment '用户ID',
    role_id int not null comment '角色ID',
    primary key (user_id, role_id)
)
    comment '用户和角色关联表' collate = utf8mb4_general_ci;

INSERT INTO gdp.sys_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO gdp.sys_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO gdp.sys_user_role (user_id, role_id) VALUES (3, 3);
