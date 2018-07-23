### SSM框架 实现简单的签到系统
#### sql 设计
```
create table user(
    user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(40) NOT NULL,
    user_email VARCHAR(100),
    user_password VARCHAR(100) NOT NULL,
PRIMARY KEY (user_id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table goods(
	good_id INT NOT NULL AUTO_INCREMENT,
	good_name VARCHAR(40) NOT NULL,
	good_price FLOAT(15,2) NOT NULL,
	PRIMARY KEY (good_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table collect(
	user_id INT NOT NULL,
	good_id INT NOT NULL,
	PRIMARY KEY (good_id, user_id))ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

#### api接口
| url           | /user/register |
| ------------- |:-------------:|
| 方法          |    POST       |
| 发送方式      | application/x-www-form-urlencoded     |
| 参数username | 不为空      |
| 参数password | 不为空,长度不能小于10      |
|成功返回 | {"code": 10000,"message": "成功","data": "注册成功"}
|失败返回 |{"code": 10001,"message": "失败","data": "用户名已存在"}
失败返回2          |{"code": 10001,"message": "失败","data": "用户名，用户密码不能为空，用户密码长度不能少于6"}



| url           | /user/login |
| ------------- |:-------------:|
| 方法          |    POST       |
| 发送方式      | application/x-www-form-urlencoded     |
| 参数username | 不为空      |
| 参数password | 不为空      |
|成功返回 | {"code": 10000,"message": "成功","data": "登录成功"}
|失败返回 |{"code": 10001,"message": "失败","data": "用户名或密码不正确"}

| url           | /user/logout |
| ------------- |:-------------:|
| 方法          |    POST       |
|成功返回 | {"code": 10000,"message": "成功","data": "退出成功"}
|失败返回 |{ "code": 10003,"message": "未登录","data": "你似乎未曾登录"}


| url           | /user/set_location |
| ------------- |:-------------:|
| 方法          |    POST       |
| 参数name | 无要求      |
|成功返回 | {"code": 10000,"message": "成功","data": "设置成功"}
|失败返回 |{"code": 10003,"message": "未登录","data": "设置地址失败"}


| url           | /checkin/create |
| ------------- |:-------------:|
| 方法          |    POST       |
| 参数detail | 非空      |
| 参数location | 非空      |
| 参数limitTime(持续时间int型分钟) | 非空      |
|成功返回 |
```
{
    "code": 10000,
    "message": "成功",
    "data": {
        "id": 5,
        "organizerId": 5,
        "detail": "工数课签到",
        "location": "大连理工大学开发区校区",
        "createTime": 1531228107000,
        "limitTime": 20
    }
}
```
失败返回 
```
{
    "code": 10001,
    "message": "地址和详情不能为空",
    "data": {
        "id": null,
        "organizerId": null,
        "detail": "工数课签到",
        "location": "大连理工大学开发区校区",
        "createTime": null,
        "limitTime": null
    }
}
```
```
{
    "code": 10003,
    "message": "用户未登录",
    "data": {
        "id": null,
        "organizerId": null,
        "detail": "工数课签到",
        "location": "大连理工大学开发区校区",
        "createTime": null,
        "limitTime": 20
    }
}
```
| url           | /checkin/list |
| ------------- |:-------------:|
| 方法          |    GET       |
|成功返回 |
```
{
    "code": 10000,
    "message": "成功",
    "data": [
        {
            "id": 6,
            "organizerId": 5,
            "detail": "工数课签到",
            "location": "大连理工大学开发区校区",
            "createTime": 1531228375000,
            "limitTime": 20
        },
        {
            "id": 5,
            "organizerId": 5,
            "detail": "工数课签到",
            "location": "大连理工大学开发区校区",
            "createTime": 1531228107000,
            "limitTime": 20
        },……
```

| url           | /checkin/join |
| ------------- |:-------------:|
| 方法          |    POST       |
| 参数id(签到记录的id) | 非空      |
|成功返回 |{ "code": 10000,"message": "成功","data": "签到成功"}
|失败返回 |{ "code": 10003, "message": "未登录", "data": "用户未登录"}
|失败返回2|{"code": 10001,"message": "失败","data": "你已签到"}

| url           | /checkin/{id}/list_users |
| ------------- |:-------------:|
| 方法          |    GET       |
| 参数id(签到记录的id) | 非空,带在url的{id}位置      |
|成功返回 返回该条目下签到的人的名字列表 |{"code": 10000,"message": "成功","data": [ "libiyi"]}

| url           | /checkin/my_checkin |
| ------------- |:-------------:|
| 方法          |    GET       |
|失败返回 |{ "code": 10003, "message": "未登录", "data": "用户未登录"}
|成功返回|
```
{
    "code": 10000,
    "message": "成功",
    "data": [
        {
            "id": 6,
            "organizerId": 5,
            "detail": "工数课签到",
            "location": "大连理工大学开发区校区",
            "createTime": 1531228375000,
            "limitTime": 20
        },
        {
            "id": 5,
            "organizerId": 5,
            "detail": "工数课签到",
            "location": "大连理工大学开发区校区",
            "createTime": 1531228107000,
            "limitTime": 20
        }
    ]
}
```
