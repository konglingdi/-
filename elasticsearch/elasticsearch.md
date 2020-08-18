

elasticsearch

### 一、elasticsearch介绍

 	Elasticsearch  是一个分布式的开源搜索和分析引擎，适用于所有类型的数据，包括文本、数字、地理空间、结构化和非结构化数据。Elasticsearch 在  Apache Lucene 的基础上开发而成，由 Elasticsearch N.V.（即现在的 Elastic）于 2010  年首次发布。Elasticsearch 以其简单的 REST 风格 API、分布式特性、速度和可扩展性而闻名。

​	Elasticsearch是用Java语言开发的，并作为Apache许可条款下的开放源码发布，是一种流行的企业级搜索引擎，Elasticsearch用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。官方客户端在Java、.NET（C#）、PHP、Python、Apache  Groovy、Ruby和许多其他语言中都是可用的。根据DB-Engines的排名显示，Elasticsearch是最受欢迎的企业搜索引擎，其次是Apache Solr，也是基于Lucene。

### 二、elasticsearch适用场景

​	Elasticsearch 在速度和可扩展性方面都表现出色，而且还能够索引多种类型的内容，这意味着其可用于多种用例：

- 应用程序搜索
- 网站搜索
- 企业搜索
- 日志处理和分析
- 基础设施指标和容器监测
- 应用程序性能监测
- 地理空间数据分析和可视化
- 安全分析
- 业务分析

### 三、elasticsearch原理介绍

#### 	1、普通搜索

​			简单来说就是根据需求，可以用等于或者模糊查询等去查询出想要的数据。

​			![image-20200705171950130](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200705171950130.png)

查询“小米“的信息：

select * from table where title like "%小米%"；

结果：

2,小米手机，为发烧而生

若查询“小米华为中兴”的信息，按照这样的方式就搜索不出来了，而百度就可以搜索出来，而且相应的关键字也标红了。另外对于平时的数据库的查询操作是很慢的，即便是创建了索引，数据量很大的时候也会很慢。

![image-20200705172957997](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200705172957997.png)

####      2、用基于lucene的搜索

**倒排索引**：倒排索引又叫反向索引（右下图）以字或词为关键字进行索引，表中关键字所对应的记录表项，记录了出现这个字或词的所有文档，每一个表项记录该文档的ID和关键字在该文档中出现的位置情况。

![image-20200705173658908](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200705173658908.png)

若查询“小米华为中兴”：会先ik分词器分词（粗分）-->匹配索引库中的单词-->根据文档id找到对应的文档内容返回结果。

索引数据： 会按照ik分词器分词（细分）

### 四、elasticsearch的使用

1、**Cluster**: 集群是一个或多个节点（服务器）的集合，这些节点（服务器）聚集在一起保存您的整个数据，并跨所有节点提供联合索引和搜索功能。集群由一个唯一的名称标识，默认情况下是“elasticsearch”。此名称很重要，因为只有将节点设置为按名称加入群集，节点才能成为群集的一部分。
请确保不要在不同的环境中重用相同的集群名称，否则可能会导致节点加入错误的集群。例如，您可以将logging dev、logging stage和logging prod用于开发、登台和生产集群。
请注意，只有一个节点的集群是有效的，而且非常好。此外，您还可能有多个独立的集群，每个集群都有自己唯一的集群名称。

2、**Node**：节点是集群的一部分、存储数据并参与集群的索引和搜索功能的单个服务器。就像集群一样，节点由一个名称标识，默认情况下，该名称是在启动时分配给节点的随机通用唯一标识符（UUID）。如果不需要默认名称，则可以定义所需的任何节点名称。此名称对于管理目的很重要，您需要标识网络中的哪些服务器对应于Elasticsearch群集中的节点。


可以将节点配置为通过集群名称加入特定集群。默认情况下，每个节点都被设置为加入一个名为elasticsearch的集群，这意味着如果您在网络上启动多个节点，并且假设它们能够相互发现，那么它们都将自动形成并加入一个名为elasticsearch的集群。

在一个集群中，您可以拥有任意多个节点。此外，如果您的网络上当前没有其他Elasticsearch节点运行，那么在默认情况下，启动单个节点将形成一个名为Elasticsearch的新单节点集群。

3、**Index**:索引是具有某种相似特征的文档的集合。例如，您可以有一个客户数据的索引，一个产品目录的另一个索引，还有一个订单数据的索引。索引由名称（必须全部为小写）标识，并且在对索引中的文档执行索引、搜索、更新和删除操作时，此名称用于引用索引。

在单个集群中，可以定义任意多个索引。

4、**Type（Deprecated in 6.0.0.）**:一种类型，过去是索引的逻辑类别/分区，允许在同一索引中存储不同类型的文档，例如一种类型用于用户，另一种类型用于博客文章。现在已经不可能在索引中创建多个类型了，在以后的版本中，类型的整个概念都将被删除。有关详细信息，请参见移除映射类型。

5、**Document:** 文档是可以编制索引的基本信息单位。例如，您可以为一个客户创建一个文档，为一个产品创建另一个文档，为一个订单创建另一个文档。本文档用JSON（JavaScript对象表示法）表示，JSON是一种普遍存在的internet数据交换格式。 

 在索引/类型中，可以存储任意多个文档。请注意，尽管文档实际驻留在索引中，但实际上必须对文档进行索引/分配到索引中的某个类型。

6、**Shards & Replicas:** 索引可能存储大量数据，这些数据可能超过单个节点的硬件限制。例如，10亿个文档的单个索引占用1TB的磁盘空间，可能不适合单个节点的磁盘，或者速度太慢，无法单独处理来自单个节点的搜索请求。 

 为了解决这个问题，Elasticsearch提供了将索引细分为多个称为碎片的部分的功能。创建索引时，可以简单地定义所需的碎片数。每个碎片本身就是一个功能齐全、独立的“索引”，可以托管在集群中的任何节点上。  

field类型：

### Core datatypes

- string

  [`text`](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/text.html) and [`keyword`](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/keyword.html)

- [Numeric datatypes](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/number.html)

  `long`, `integer`, `short`, `byte`, `double`, `float`, `half_float`, `scaled_float`

- [Date datatype](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/date.html)

  `date`

- [Boolean datatype](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/boolean.html)

  `boolean`

- [Binary datatype](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/binary.html)

  `binary`

- [Range datatypes](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/range.html)

  `integer_range`, `float_range`, `long_range`, `double_range`, `date_range`

**在spring项目中的使用：**

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client</artifactId>
    <version>7.6.2</version>
</dependency>

<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.6.2</version>
</dependency>
```

**在spring-boot项目中的使用:**

```xml
<properties>
    <java.version>1.8</java.version>
    <!-- 指定es版本 -->
    <elasticsearch.version>7.7.1</elasticsearch.version>
</properties>
  
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

**spring-boot项目中配置RestHighLevelClient实例对象**

```java
@Configuration
public class ElasticSearchClientConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200,"http")));
        return client;
    }
}
```

**高亮：**

```java
@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    //实现搜索功能
    public List<Map<String, Object>> searchHighlightPage(String keyword, int page, int size) throws IOException {
        if (page <= 1) {
            page = 1;
        }
        //创建搜索请求
        SearchRequest searchRequest = new SearchRequest("索引名");
        //构造搜索参数
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//设置需要精确查询的字段
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("filed", keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.from((page - 1) * size);
        searchSourceBuilder.size(size);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮字段
        highlightBuilder.field("filed");
        //如果要多个字段高亮,这项要为false
        highlightBuilder.requireFieldMatch(true);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        
		//下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等   
		highlightBuilder.fragmentSize(800000); //最大高亮分片数
   		highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //解析高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField field= highlightFields.get("field");
            if(field!= null){
                Text[] fragments = field.fragments();
                String n_field = "";
                for (Text fragment : fragments) {
                    n_field += fragment;
                }
                //高亮标题覆盖原标题
                sourceAsMap.put("field",n_field);
            }
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
}
```



spring-boot集成es:https://www.cnblogs.com/linlf03/p/12828414.html

启动head(后台)  ./elasticsearch-head/node_modules/grunt/bin/grunt server &

高亮：https://blog.csdn.net/weixin_43155742/article/details/106720910?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~first_rank_v2~rank_v25-2-106720910.nonecase&utm_term=es%E9%AB%98%E4%BA%AE%E6%98%BE%E7%A4%BAjava%E5%AE%9E%E7%8E%B0

