# spring-thymeleaf-test : 多个 web 项目共享同一套模板的演示

----

## 主要设计思路：

- 前后端分离
- 各个项目之间也尽量分离
- 后端项目中减少前端引用的第三方的 JavaScript/CSS/image 等 
- 公共的 JavaScript 和 CSS 文件放在静态服务器上，从 nodejs 直接部署，这里并没有演示这个部分
- datatables 和 chart.js 的逻辑均已经全部封装好，不需要在页面中额外进行调整
- 使用 mock.js 编写纯前端的页面并测试基本流程
- 后端就只需要包含具体每个页面的 html 和 JavaScript 即可
- 多个 Java Web 项目希望能共用同一套 thymeleaf 模板

## 实现方式：

- 使用 thymeleaf-layout-dialect 将通用的页面风格封装到 `test-commons` 中

## 如何使用这个例子？

- 检出项目并配置好 Maven 相关逻辑，使之成为有效的 Java 项目
- 将 `test-webapp1` 和 `test-webapp2` 部署到 web 服务器中（这里使用的是 tomcat8 ）
- 启动 web 服务器，分别查看 `/app1` 和 `/app2` 
- 分别查看其中的 `page1` 和 `page2`，对应相应的代码，着重注意以下逻辑：
  - `index` 是每个 web 项目自己的 thymeleaf 模板，没有引用其他模板
  - `page1` 是每个 web 项目中引用自己的 `index` 中的 `<nav>` 部分
  - `page2` 是每个 web 项目中自己写 `<div layout:fragment="content">` 然后引用 `test-commons` 中的 `inc/all.html` 并把主要内容替换掉的逻辑
- 理解整个思路之后，应用到自己的项目中。

## FAQ

- 为什么依赖项找不到？

答：暂时没顾得上好好写，因此还引用了我们公司内部私有的 pom 和一些依赖项。着急用的话暂时先自己改一下。
