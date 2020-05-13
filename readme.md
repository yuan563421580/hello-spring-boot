说明： hello-spring-boot 项目是 一个学习 spring boot 整合相关知识的项目，记录学习过程中的相关知识及遇到的相关问题。
    选择版本为 2.1.6.RELEASE 
   
一、创建工程（JDk1.8 + Maven + Web）
    1.创建工程
        方式1.File -> New Project -> Spring Initializr
        方式2.在浏览器中输入 https://start.spring.io/ 进行创建
        Group : com.yuansb
        Artifact : hello-spring-boot
        Package name : com.yuansb.hello.spring.boot
    2.修改 pom.xml 版本号 2.1.6.RELEASE
    temp1.编写 TestIndexController 用于测试逻辑
    temp2.测试地址： http://localhost:8180/boot/
    
二、集成静态html
    1.在 pom.xml 中引入 spring-boot-starter-thymeleaf 、nekohtml 依赖
    2.创建 TestToHtmlController 实现类，注意使用@Controller注解，使用@RestController注解会报错
    3.在 templates 文件夹下创建 index.html 页面
    temp.测试地址： http://localhost:8180/boot/test/html/index

三、多环境配置
    将 application.properties 修改成 application.yml ; 
    同时建立 application-local.yml 、application-dev.yml 和 application-prod.yml ；
    在 application.yml 中 spring.profiles.active 的值来控制版本 ; 
    因为本地学习为模拟操作，可以将 application-*.yml 文件配置成一样的。
    
四、连接数据库mysql 、配置 mybatis、 配置org.mybatis.generator生成代码
    1.连接mysql数据库 在 pom.xml 中 引入 mysql-connector-java 驱动 ； 引入 tk.mybatis 配置
    2.使用 HikariCP 连接池 进行连接数据库 在pom.xml中增加依赖 HikariCP ； 同时在 spring-boot-starter-jdbc 中排除 tomcat-jdbc
    3.在 application.yml 文件中配置 datasource 、 实体类的存放路径: mybatis.type-aliases-package 和 mybatis.mapper-locations
    3.创建通用父级接口 java -> tk.mybatis.mapper -> MyMapper
    4.自动生成代码配置：
        01). 创建实体类存放路径 Package：domain (com.yuansb.hello.spring.boot.domain)
        02). 在 src/main/resources 目录下创建 jdbc.properties 数据源配置
        03). 在 src/main/resources/generator/ 目录下创建 generatorConfig.xml 配置文件(自动生成的配置)
        04). 在 pom.xml 中增加 mybatis-generator-maven-plugin 插件
    5.引入分页插件 pagehelper 在 pom.xml 中引入依赖
    temp1.编写 TestCrudController 和 TestCrudService 用于测试逻辑
    temp2.后续补充具体测试测试路径
        
五、配置多数据源：
    1.application-*.yml 配置 数据源 ds1 和 ds2 
        注意将原有的 spring.datasource.url 改成 spring.datasource.jdbc-url ， 否则可能出现(错误.三)
    2.数据源类型枚举：创建Package：common -> 创建Interface：ContextConst
    3.数据源持有类：创建Package：dataSource -> 创建class：DataSourceContextHolder 
    4.动态数据源实现类：Package：dataSource -> 创建class：DynamicDataSource 
    5.定义数据源：Package：dataSource -> 创建class：MutiplyDataSource ; 
        后续新增加数据源需要 在 application-*.yml 新配置连接，在ContextConst建立枚举，在MutiplyDataSource中实现声明
    6.数据源切换
        (6.1).使用切面实现数据源切换：具体看 DataSourceSign 和 DynamicDataSourceAspect
        (6.2).在方法中实现切换 DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.DS_2.name());
            特殊说明：因为在切面中定义了，如果在方法中进行切换会先执行切面中的切换
            建议受用一种，根据业务实际建议在方法中实现切换，可以屏蔽切面实现数据源切换
    temp1.编写 TestDataSourceController 和 TestDataSourceService 用于测试逻辑
    temp2.测试数据源切换操作
        http://localhost:8180/boot/test/ds/inDs1Deal
        http://localhost:8180/boot/test/ds/inDs2Deal
        http://localhost:8180/boot/test/ds/inDs2AspectDeal
        
六、配置Redis(Jedis)
    1.在pom.xml中引入 spring-boot-starter-data-redis 和 jedis 依赖
    2.在application-*.yml中配置redis相关信息：spring.redis，注意 pool 配置和spring boot版本有关
    3.创建Package：config -> 创建class：JedisConfig
    4.创建Package：utils -> 创建class：RedisUtils
    temp1.测试redis配置
        重启服务，控制台会打印出相关信息：JedisPool注入成功！
        
七、使用Redis如何实现Session共享
    利用已经配置的redis相关信息继续进行实现Session共享相关操作
    1.在pom.xml中需要新引入spring-session-data-redis
    2.在RedisSessionConfig配置中添加@EnableRedisHttpSession开始spring session支持
    3.
    4.修改端口重启2次：需要Run -> Edit Configurations -> 打钩 allow parallel run ； 
        第2次重启失败可能是因为引入devtools报错
    temp1.编写 TestRedisSessionController 用于测试逻辑
    temp2.测试是否实现session共享 需要模拟多主机测试，修改端口8181测试完成后改回8180
        http://localhost:8180/boot/test/redis/session/intoSession
        http://localhost:8180/boot/test/redis/session/getSession
        http://localhost:8181/boot/test/redis/session/intoSession
        http://localhost:8181/boot/test/redis/session/getSession
        
八、根据环境加载不同的properties配置文件
    自定义properties文件来配置自定义的内容，避免频繁改环境引起配置文件频繁修改，可以实现不同的环境加载不同的properties自定义的配置文件
    1.创建Package : resources.conf -> 创建properties : boot-local.properties 、boot-dev.properties 、 boot-prod.properties
        实现不同环境的内容配置
    2.创建Package : config.property -> 创建class : PropertyLocalConfig 、PropertyDevConfig 、PropertyProdConfig
    3.创建Package : factory -> 创建class : PropertyFactory 配置 @SuppressWarnings("all") 实现读取 Property*Config
        实现不同环境的内容声明赋值
    temp1.在 TestIndexController 中编写 testPropertyInfo() 方法测试
    temp2.local环境下测试地址：http://localhost:8180/boot/test/property/say
          修改为dev后重启服务测试地址：http://localhost:8280/boot/test/property/say

九、配置logback日志
    1.在 resource 目录下创建 logback-spring.xml (官方推荐，因为带spring后缀的可以使用<springProfile>这个标签)
        实现区分多环境配置 和 控制台打印
    2.在 application.yml 中配置 logging.config
        
十、生成唯一key值工具类，包含 redis 和 雪花算法
    （一）、redis生成
    （二）、雪花算法生成
    temp1.在 TestGenerateKey 中编写测试用例
    redis生成：http://localhost:8180/boot/test/generate/key/1
    雪花算法生成：http://localhost:8180/boot/test/generate/key/2
    
问题出现及解决
    (一).运行时无问题，install报错：  Tests run: 3, Failures: 0, Errors: 3, Skipped: 0, Time elapsed: 0.091 s <<< FAILURE!
        在pom.xml中配置 <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                            <artifactId>maven-surefire-plugin</artifactId>
                            <configuration>
                                <skip>true</skip>
                            </configuration>
                       </plugin>    
    (二).the dependencies of some of the beans in the application context form a cycle
        是由于代码中存在着循环依赖导致的
            sprint boot 低版本可以使用 spring.datasource.initialize=false （默认为true） 来解决，
            升级到 sprint boot2.1.2后就发现被启用了 @SpringBootApplication(exclude = DataSourceAutoConfiguration.class) 解决
                其他解决方案(未进行验证)：数据源 MutiplyDataSource 中 dynamicDataSource() 方法增加注解@DependsOn({ "ds1DataSource", "ds2DataSource"})
    (三).java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.
        修改 application.yml 配置 ： spring.datasource.url --> spring.datasource.jdbc-url        