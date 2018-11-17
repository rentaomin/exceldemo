### Excel 解析入库 Demo

- 目的
```$xslt
   1、提供解析大数据量Excel2003版和2007+版的解析工具类
   2、降低解析Excel 时内存占用过高，或者内存溢出报错
   3、加快解析Excel的速度，提高响应时间
   4、保存解析后的数据到数据库
   5、能够适应小规模的并发量
```
- 方案介绍
```$xslt
    在常规企业应用系统中，大部分会有批量导入或导出数据
到Excel,该功能实现通常都是使用ApachePOI组件，通过该组件
可实现对Excel的读和写操作，但是在读取Excel时，会一次性把
数据加载到内存，通过自身的解析器将Excel转换为XML进行解
析，该方式对于大数据量的Excel解析处理，会导致内存溢出或
者占用过高，因此，我们可以通过限制读入内存的数据和优化XML
解析的过程，来实现高效率、大批量的解析Excel。
```
### 组件介绍
 
- POI介绍
```$xslt
    Apache POI用于Java处理Excel的主要组件,该组件是
由Java编写，专门用于处理MS Word、PPT、Excel等产
品的开源组件，为Java提供API用于操作Microsoft 
Office格式的读和写的功能。
```
- Apache xerces 介绍
```$xslt
    Apache xerces是由Apache组织所推动的一项XML文档解析
开源项目, 是一个开放源代码的XML语法分析器。
``` 
- SAXParser解析器
```$xslt
	该SAXParser解析器解析XML时,并不需要读入整个XML
文档，而文档的读入过程也就是SAXParser的解析过程。它
是事件驱动的，所谓事件驱动，是指一种基于回调
（callback）机制的程序运行方法，解析开始之前，需要向
XMLReader注册一ContentHandler，也就是相当于一个事件监
听器，实现自定义处理，它逐行扫描文档，一边扫描一边解析
，从而能够有效的降低解析Excel所占用内存过高的问题，提高
解析速度。
```
- 生产者和消费者多线程模式
```$xslt
    生产者和消费者模式为多线程中的一种重要设计模式，
数据的提供方可形象的称为数据的生产者，而数据的加工
方则可以称为消费者，为了避免生产者生产数据的速率比
消费者快，我们在生产者和消费者中间引入一个缓冲区
（Channel,或者队列），对二者进行解耦，生产者将其
“生产”的数据放入通道，消费者从相应的通道中消费数
据，生产者和消费者各自运行在各自的线程中。
```
### 使用指导 

- 导入项目，CommonUtil类公共参数配置 
```$xslt
    /** office 2003 版本的excel 文件类型后缀名 */
    private  final static String EXCEL_2003_FILE_TYPE = ".xls";

    /** office 2007+ 版本的excel 文件类型后缀名 */
    private final static String EXCEL_2007_FILE_TYPE = ".xlsx";

    /** 每次提交的事务数据量 */
    private final static int DEFAULT_TRANSCATION_COMMIT_NUMBER = 20000;

    /** 默认线程数 */
    private static  final int DEFAULT_THREAD_NUM = 10;
```
- 重写`CacheQueueDataHandler`接口的方法， 实现自己的业务逻辑，如
```$xslt
    /**
     * 处理自己的业务逻辑
     * @param vector 此处为Excel 行记录集合
     */
    public void handlerData(Vector vector) {
        this.saveRowProperties(vector);
    }
```

- 需要解析Excel文件的地方调用该方法，如下
```$xslt
String xlsxFile = "F:/pay.xlsx";
ExcelParseUtil excelParseUtil = new ExcelParseUtil();
excelParseUtil.parseExcel(xlsxFile);
```
### 测试结果

- Excel 2003 版本测试
```$xslt
Excel版本：Excel2003版  文件类型：xls
Excel数据量：65533					Excel列数：35列
Excel 文件大小：32.5M
测试方法：
	采用Jdbc预处理方式进行提交，每次提交事务数据量为20000
测试结果：解析该测试excel文件共花费时间约：8秒 。
```
- Excel 2007 版本测试
```$xslt
测试环境：
Excel版本：Excel2007版  文件类型：xlsx
Excel数据量：56万多				Excel列数：35列
Excel 文件大小：69.6M
测试方法：
	采用Jdbc预处理方式进行提交，每次提交事务数据量为20000
测试结果：解析该测试excel文件共花费时间约：53秒 ,解析过程中内存消耗并无明显变化。

```