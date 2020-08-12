package codegenerator.yaorma;

import java.io.File;
import java.sql.Connection;

import org.yaorma.codeGenerator.generateOrmForSchema.GenerateOrmForSchema;

import com.nach.core.util.file.FileUtil;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DvoCodeGenerator {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			conn = getConn();
			String schemaName = "cosmos";
			String packageName = "com.nachc.cad.cosmos.dvo";
			File destDir = FileUtil.getFile("src/main/java/com/nachc/cad/cosmos/dvo");
			if(destDir.exists()) {
				log.info("Deleting dir:\t" + destDir.getCanonicalPath());
				destDir.delete();
			}
			log.info("Creating dir:\t" + destDir.getCanonicalPath());
			destDir.mkdir();
			log.info("Writing to:\t" + destDir.getCanonicalPath());
			GenerateOrmForSchema.execute(conn, schemaName, packageName, destDir);
			log.info("Done!");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private static Connection getConn() {
		return ConnectionUtil.getMysqlConnection();
	}

}
