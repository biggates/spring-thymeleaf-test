# spring-thymeleaf-test : 多个 web 项目共享同一套模板的演示

## 主要设计思路：

- 后端项目中减少前端引用的第三方的 JavaScript/CSS/image 等 
- 公共的 JavaScript 和 CSS 文件放在静态服务器上，从 nodejs 直接部署，这里并没有演示这个部分
- datatables 和 chart.js 的逻辑均已经全部封装好，不需要在页面中额外进行调整
- 使用 mock.js 编写纯前端的页面并测试基本流程
- 多个 Java Web 项目希望能共用同一套 thymeleaf 模板

## 期望

- 前后端分离
- 各个后端项目之间也尽量分离
- 做每个页面之前写清楚前后端沟通的接口文档
- 之后，前端工程师只需要在 grunt 环境里做具体每个页面的 html 、 JavaScript 和 mock，例：表单、验证、图表，等；
- 后端工程师只需要做 Controller 、service 、repo 和 entity
- 然后后端工程师把 html 和 JavaScript 复制到 Java web 工程中，加 `layout` 相关内容 
- 因为只是复制过来，因此原理上前端工程师后续的修改内容还可以通过 patch 的形式打到后端项目中

## 实现方式：

- 通过配置 `thymeleaf` 的模板目录到 `classpath` 下（而不是传统的 `WEB-INF` 下），实现 `test-commons` 公用 jar 包项目
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

## 可能用到的参考资料

- [Thymeleaf: Using Thymeleaf](www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html)
- [ultraq/thymeleaf-layout-dialect](https://github.com/ultraq/thymeleaf-layout-dialect)

## FAQ

- 使用场景是？

答：场景简单介绍如下：

  - 没有专业的前端工程师，因此纯 nodejs 环境跑不转，大头还是得 Java 工程师来写
  - 传统方式中，每个 Java Web 工程里除了一堆 jar 包（现在用 Maven 解决的差不多了）之外，还有一大堆 JavaScript/CSS/images 
  - 旧的协作模式中，前端工程师和后端工程师在写 thymeleaf 的 html 页面时都很麻烦（`th` 标签长而复杂）

