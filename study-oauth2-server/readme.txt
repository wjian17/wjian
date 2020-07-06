1、WebAsyncManagerIntegrationFilter
将Security上下文与Spring Web中用于处理异步请求映射的 WebAsyncManager 进行集成。
2、SecurityContextPersistenceFilter
在每次请求处理之前将该请求相关的安全上下文信息加载到SecurityContextHolder中，然后在该次请求处理完成之后，将SecurityContextHolder中关于这次请求的信息存储到一个“仓储”中，然后将SecurityContextHolder中的信息清除
例如在Session中维护一个用户的安全信息就是这个过滤器处理的。
3、HeaderWriterFilter
用于将头信息加入响应中
4、CsrfFilter
用于处理跨站请求伪造
5、LogoutFilter
用于处理退出登录
6、UsernamePasswordAuthenticationFilter
用于处理基于表单的登录请求，从表单中获取用户名和密码。默认情况下处理来自“/login”的请求。
从表单中获取用户名和密码时，默认使用的表单name值为“username”和“password”，这两个值可以通过设置这个过滤器的usernameParameter 和 passwordParameter 两个参数的值进行修改。
7、DefaultLoginPageGeneratingFilter
如果没有配置登录页面，那系统初始化时就会配置这个过滤器，并且用于在需要进行登录时生成一个登录表单页面。
8、BasicAuthenticationFilter
处理请求头信息，DigestAuthenticationFilter,头需要含有Authentication
9、RequestCacheAwareFilter
用来处理请求的缓存
10、SecurityContextHolderAwareRequestFilter
维护SecurityContext，封装ServletRequest
11、AnonymousAuthenticationFilter
匿名，不登录使用
12、SessionManagementFilter
回话管理，用户开启回话数量
13、ExceptionTranslationFilter
处理 AccessDeniedException 和 AuthenticationException 异常，处理权限异常
14、FilterSecurityInterceptor（授权，资源是否可访问的决策）
AbstractInterceptUrlConfigurer.createFilterSecurityInterceptor
15、RememberMeAuthenticationFilter的作用是, 当用户没有登录而直接访问资源时, 从cookie里找出用户的信息, 如果Spring Security能够识别出用户提供的remember me cookie, 用户将不必填写用户名和密码, 而是直接登录进入系统.




 如下为其主要过滤器  
        WebAsyncManagerIntegrationFilter 
        SecurityContextPersistenceFilter 
        HeaderWriterFilter 
        CorsFilter 
        LogoutFilter
        RequestCacheAwareFilter
        SecurityContextHolderAwareRequestFilter
        AnonymousAuthenticationFilter
        SessionManagementFilter
        ExceptionTranslationFilter
        FilterSecurityInterceptor
        UsernamePasswordAuthenticationFilter
        BasicAuthenticationFilter


框架的核心组件
      SecurityContextHolder：提供对SecurityContext的访问
      SecurityContext,：持有Authentication对象和其他可能需要的信息
      AuthenticationManager 其中可以包含多个AuthenticationProvider
      ProviderManager对象为AuthenticationManager接口的实现类
      AuthenticationProvider 主要用来进行认证操作的类 调用其中的authenticate()方法去进行认证操作
      Authentication：Spring Security方式的认证主体
      GrantedAuthority：对认证主题的应用层面的授权，含当前用户的权限信息，通常使用角色表示
     UserDetails：构建Authentication对象必须的信息，可以自定义，可能需要访问DB得到
      UserDetailsService：通过username构建UserDetails对象，通过loadUserByUsername根据userName获取UserDetail对象 （可以在这里基于自身业务进行自定义的实现  如通过数据库，xml,缓存获取等） 
