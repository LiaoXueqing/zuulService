# zuulService

zuul service, user service, todo service三个项目是进行微服务拆分后的结果。

其中zuul service 项目实现鉴权和路由。

week4-microsevice-userService是user service，用户登录时，zuul service进行登录验证，生成token。

week4-microsevice-todoService是todo service，访问todos时，zuul service对用户进行验证，验证通过则返回内容。

配合前端项目week3-todo-list正常工作
