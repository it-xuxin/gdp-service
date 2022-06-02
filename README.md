# Nacos + Gateway + Security + Oauth2 项目

## gdp-service-admin     > 后台管理系统
## gdp-service-auth      > Auth认证中心
## gdp-service-common    > 基础组件
## gdp-service-gateway   > 网关
## gdp-service-user      > 用户系统

## 启动 nacos
```
cd gdp-service/document/nacos-docker
docker-compose -f example/standalone-derby.yaml up
```

## 数据
```
cd gdp-service/document/mysql
```


## 请求

> Authorization: Basic -> 获取:根据sys_oauth_client表 clientId:client_secret 字段base64，如base64(client:123456)
> 
> Authorization: Bearer -> 获取:登录后返回
### 手机号验证码登录
```
curl --location --request POST '127.0.0.1:9999/auth/oauth/login/mobile/code' \
--header 'Authorization: Basic Z2RwLXNlcnZpY2UtdXNlci1ib290OkxLZzdpY0ZSL25iSVdBVHZBb3FZRXl5b080bkJVZjUy' \
--header 'Cookie: JSESSIONID=29832888879BF0917E9AF6BDE9C91A25' \
--form 'mobile="15313151721"' \
--form 'code="000000"'
```

### 用户名密码和图片验证码登录
```
curl --location --request POST '127.0.0.1:9999/auth/oauth/login/username/password' \
--header 'Authorization: Basic Z2RwLWFkbWluLXdlYjpMS2c3aWNGUi9uYklXQVR2QW9xWUV5eW9PNG5CVWY1Mg==' \
--header 'Cookie: JSESSIONID=29832888879BF0917E9AF6BDE9C91A25' \
--form 'username="admin"' \
--form 'password="123456"' \
--form 'code="10"' \
--form 'uuid="ca282088d262460cad53807208d9b9fb"'
```

### 获取图片验证码
```
curl --location --request GET 'http://127.0.0.1:9999/admin/api/v1/captcha/code' \
--header 'Cookie: JSESSIONID=29832888879BF0917E9AF6BDE9C91A25'
```

### 用户系统文档地址
```
http://127.0.0.1:9999/u/doc.html

Authorize
username:admin
password:123456
clientId:client
clientSecret:123456
```