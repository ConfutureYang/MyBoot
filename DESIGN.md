一、登录注册模块
1,注册  2,登录  3，退出登录 4,获取用户详细信息

DAO层
table: user_info user_password
dataObject: UserInfo UserPassword
sql operate: mapper example 
dao interface: insert, getUserPassword, 
service  createUser, userValid, GetUserInfo

4月13日 16:49
1，字段检测，最大最小值，非空，必须有
2，redis写入的值中带有 斜杠
3，注册逻辑完善，需要判断验证码是否正确
4，获取验证码逻辑的完善，需要判断用户是否过于频繁
5，事务中异常逻辑的捕获，事务部分逻辑更加明白

4月14日
1，各类异常捕获，能够给前端提供一个格式固定统一的返回
2，获取验证码逻辑的完善，需要判断用户是否过于频繁
3，注册和登录逻辑的完善
4，实现接口的登录验证
