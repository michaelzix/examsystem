package test;
import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class Generator {
    private static Prop app = PropKit.use("app.properties");
    public static void main(String[] args) {
        // base model 所使用的包名 哈哈
        String baseModelPkg = "exam.model.base";
// base model 文件保存路径
        String baseModelDir = PathKit.getWebRootPath() + "/src/test/java/exam/model/base";
        System.out.println(baseModelDir);
// model 所使用的包名
        String modelPkg = "exam.model";
// model 文件保存路径
        String modelDir = baseModelDir + "/..";
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(app.get("jdbc.driverClass"));
        dataSource.setUsername(app.get("jdbc.username"));
        dataSource.setPassword(app.get("jdbc.password"));
        dataSource.setUrl(app.get("jdbc.url"));
        com.jfinal.plugin.activerecord.generator.Generator gernerator = new com.jfinal.plugin.activerecord.generator.Generator(dataSource, baseModelPkg, baseModelDir, modelPkg, modelDir);
        gernerator.generate();
    }
}
