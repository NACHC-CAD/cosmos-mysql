package com.nachc.cad.cosmos.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.nach.core.util.file.FileUtil;
import com.nachc.cad.cosmos.util.conn.ConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateDatabase {

	public static void init() throws Exception {
		log.info("Initializing database...");
		// get the files
		List<File> files = getFiles();
		// get the connection
		log.info("Getting database connection");
		Connection conn = ConnectionUtil.getMysqlConnection();
		// execute the scripts
		for (File file : files) {
			executeSqlScript(file, conn);
		}
		// done
		String msg = "Done running scripts.\n\n";
		msg += "=========================================================================\n";
		msg += "Scritps executed: \n";
		for (File file : files) {
			msg += "\t" + file.getName() + "\n";
		}
		msg += "=========================================================================\n";
		msg += "\n\nDone.\n\n";
		log.info(msg);
	}

	private static List<File> getFiles() {
		String fileName = "sql";
		File dir = FileUtil.getFromProjectRoot(fileName);
		List<File> files = FileUtil.list(dir);
		for (int i = (files.size() - 1); i >= 0; i--) {
			File file = files.get(i);
			if (file.getName().toLowerCase().startsWith("000")) {
				files.remove(i);
			}
			if(file.isDirectory() == true) {
				files.remove(i);
			}
		}
		return files;
	}

	private static void executeSqlScript(File file, Connection conn) throws Exception {
		Reader reader = new BufferedReader(new FileReader(file));
		log.info("Running script from file: " + file.getCanonicalPath() + "\n\n------------------------------------------------------------------------------");
		ScriptRunner sr = new ScriptRunner(conn);
		sr.setAutoCommit(true);
		sr.setStopOnError(true);
		sr.runScript(reader);
		log.info("Done running script from file: " + file.getCanonicalPath() + "\n------------------------------------------------------------------------------\n\n");
	}

}
