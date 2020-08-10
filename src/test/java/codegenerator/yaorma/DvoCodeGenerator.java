package codegenerator.yaorma;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.yaorma.codeGenerator.generateOrmForSchema.GenerateOrmForSchema;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DvoCodeGenerator {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			conn = getConn();
			String schemaName = "cosmos";
			String packageName = "com.nachc.cad.cosmos.dao";
			File destDir = FileUtil.getFile("src/main/java/com/nachc/cad/cosmos/dao");
			log.info("Writing files to: " + destDir.getCanonicalPath());
			GenerateOrmForSchema.execute(conn, schemaName, packageName, destDir);
			log.info("Done!");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cosmos", "greshje", "changeme");
			return conn;
			// here sonoo is database name, root is username and password
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
